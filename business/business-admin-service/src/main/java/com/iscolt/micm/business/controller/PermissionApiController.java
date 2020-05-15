package com.iscolt.micm.business.controller;

import com.iscolt.micm.business.dto.params.PermissionApiParam;
import com.iscolt.micm.business.dto.params.RoleParam;
import com.iscolt.micm.commons.constant.SystemConstant;
import com.iscolt.micm.commons.dto.ResponseResult;
import com.iscolt.micm.commons.model.dto.ResponseCode;
import com.iscolt.micm.commons.model.dto.SysPermissionApiTreeDTO;
import com.iscolt.micm.provider.api.SysPermissionApiService;
import com.iscolt.micm.provider.api.SysPermissionService;
import com.iscolt.micm.provider.api.SysRolePermissionService;
import com.iscolt.micm.provider.api.SysUserService;
import com.iscolt.micm.provider.entity.SysPermission;
import com.iscolt.micm.provider.entity.SysPermissionApi;
import com.iscolt.micm.provider.entity.SysRole;
import com.iscolt.micm.provider.entity.SysUser;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/4
 * @see: com.iscolt.micm.business.controller
 * @version: v1.0.0
 */
@RestController
@RequestMapping(value = "admin/permission/api")
public class PermissionApiController {


    @Reference(version = "1.0.0")
    private SysUserService sysUserService;

    @Reference(version = "1.0.0")
    private SysPermissionApiService sysPermissionApiService;

    @Reference(version = "1.0.0")
    private SysPermissionService sysPermissionService;

    @Reference(version = "1.0.0")
    private SysRolePermissionService sysRolePermissionService;

    /**
     * 判断权限和组织ID获取所能管理的权限
     * @return
     */
    @GetMapping(value = "")
    public ResponseResult<List<SysPermissionApiTreeDTO>> list() throws Exception {
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = sysUserService.get(authentication.getName());
        List<SysPermissionApiTreeDTO> sysPermissionApiTreeDTOS = sysPermissionApiService.selectPermissionApiTreeByTenantId(sysUser.getTenantId());
        return new ResponseResult<List<SysPermissionApiTreeDTO>>(ResponseCode.OK.code(), "查询成功", sysPermissionApiTreeDTOS);
    }

    /**
     * 添加权限接口
     * @param permissionApiParam
     * @return
     */
    @PostMapping(value = "add")
    public ResponseResult<PermissionApiParam> add(@RequestBody PermissionApiParam permissionApiParam){
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = sysUserService.get(authentication.getName());

        SysPermission sysPermission = new SysPermission();
        SysPermissionApi sysPermissionApi = new SysPermissionApi();

        sysPermission.setTenantId(sysUser.getTenantId()); // 设置权限所属租户
        sysPermission.setType(SystemConstant.PERMISSION_API); // 设置权限类型

//        BeanUtils.copyProperties(sysPermission, permissionApiParam); // 替换
        sysPermission.setName(permissionApiParam.getName());
        sysPermission.setDescription(permissionApiParam.getDescription());
        sysPermission.setParentId(permissionApiParam.getParentId());

        // TODO 可以换成Feign内部通信
        SysPermission result = sysPermissionService.create(sysPermission);

        // 如果SysPermission添加成功
        if (result != null) {
            sysPermissionApi.setPermissionId(result.getId());
            sysPermissionApi.setUrl(permissionApiParam.getUrl());
            sysPermissionApiService.create(sysPermissionApi);
            return new ResponseResult<PermissionApiParam>(ResponseCode.OK.code(), ResponseCode.OK.message());
        }

        return new ResponseResult<PermissionApiParam>(ResponseCode.FAIL.code(), ResponseCode.FAIL.message());
    }

    /**
     * 更新权限接口
     * @param permissionApiParam
     * @return
     */
    @PostMapping(value = "update")
    public ResponseResult<Void> update(@RequestBody PermissionApiParam permissionApiParam) {
        SysPermission sysPermission = new SysPermission();
        SysPermissionApi sysPermissionApi = new SysPermissionApi();
        sysPermission = sysPermissionService.getById(permissionApiParam.getId());
//        BeanUtils.copyProperties(sysPermission, permissionApiParam);
        sysPermission.setName(permissionApiParam.getName());
        sysPermission.setDescription(permissionApiParam.getDescription());
        sysPermission.setParentId(permissionApiParam.getParentId());
        SysPermission update = sysPermissionService.update(sysPermission);

        if (update != null) {
            // 通过权限id 获取权限API, 一对一关系
            sysPermissionApi = sysPermissionApiService.getByPermissionId(update.getId());
            sysPermissionApi.setUrl(permissionApiParam.getUrl());
            sysPermissionApiService.update(sysPermissionApi);
            return new ResponseResult<Void>(ResponseCode.OK.code(), ResponseCode.OK.message());
        }

        return new ResponseResult<Void>(ResponseCode.FAIL.code(), ResponseCode.FAIL.message());
    }

    /**
     * 根据id删除权限接口
     * @param id
     * @return
     */
    @GetMapping(value = "delete/{id}")
    public ResponseResult<Void> delete(@PathVariable(value = "id") int id) {
        SysPermission result = sysPermissionService.removeById(id);

        // 删除所有角色权限的关联, 权限对应的接口
        if (result != null) {
            int apiResult = sysPermissionApiService.removeByPermissionId(id);
            int rolePermission = sysRolePermissionService.removeByPermissionId(id);
            return new ResponseResult<Void>(ResponseCode.OK.code(), "删除了" + apiResult +"条接口," + rolePermission + "条角色权限关联!");
        }

        return new ResponseResult<Void>(ResponseCode.FAIL.code(), ResponseCode.FAIL.message());
    }
}
