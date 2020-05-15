package com.iscolt.micm.business.controller;

import com.iscolt.micm.business.dto.params.RoleParam;
import com.iscolt.micm.commons.dto.ResponseResult;
import com.iscolt.micm.commons.model.dto.ResponseCode;
import com.iscolt.micm.commons.model.dto.SysRoleTreeDTO;
import com.iscolt.micm.provider.api.SysRolePermissionService;
import com.iscolt.micm.provider.api.SysRoleService;
import com.iscolt.micm.provider.api.SysUserRoleService;
import com.iscolt.micm.provider.api.SysUserService;
import com.iscolt.micm.provider.entity.SysRole;
import com.iscolt.micm.provider.entity.SysUser;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色控制器
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/1
 * @see: com.iscolt.micm.business.controller
 * @version: v1.0.0
 */
@RestController
@RequestMapping(value = "admin/roles")
public class RoleController {

    @Reference(version = "1.0.0")
    private SysRoleService sysRoleService;

    @Reference(version = "1.0.0")
    private SysUserService sysUserService;

    @Reference(version = "1.0.0")
    private SysUserRoleService sysUserRoleService;

    @Reference(version = "1.0.0")
    private SysRolePermissionService sysRolePermissionService;

    /**
     * 判断权限和组织ID获取所能管理的角色
     * @return
     */
    @GetMapping(value = "")
    public ResponseResult<List<SysRoleTreeDTO>> list() throws Exception {
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = sysUserService.get(authentication.getName());
        List<SysRoleTreeDTO> sysRoleTreeDTOS = sysRoleService.selectRoleTreeByTenantId(sysUser.getTenantId());
        return new ResponseResult<List<SysRoleTreeDTO>>(ResponseCode.OK.code(), "查询成功", sysRoleTreeDTOS);
    }

    /**
     * 添加角色
     * @return
     */
    @PostMapping(value = "add")
    public ResponseResult<RoleParam> add(@RequestBody RoleParam roleParam){
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = sysUserService.get(authentication.getName());
        // TODO RoleParam 换成 RoleDTO
        SysRole sysRole = new SysRole();
        sysRole.setParentId(roleParam.getParentId());
        sysRole.setDescription(roleParam.getDescription());
        sysRole.setEnname(roleParam.getEnname());
        sysRole.setName(roleParam.getName());
        sysRole.setTenantId(sysUser.getTenantId());
        SysRole result = sysRoleService.create(sysRole);
        BeanUtils.copyProperties(result, roleParam); // 替换
        return new ResponseResult<RoleParam>(ResponseCode.OK.code(), ResponseCode.OK.message(), roleParam);
    }

    /**
     * 更新角色
     * @param roleParam
     * @return
     */
    @PostMapping(value = "update")
    public ResponseResult<Void> update(@RequestBody RoleParam roleParam) {
        SysRole sysRole = new SysRole();
        sysRole = sysRoleService.getById(roleParam.getId()); // 补充其他信息
        BeanUtils.copyProperties(roleParam, sysRole); // 替换
        // TODO 可以验证角色是否存在

        SysRole result = sysRoleService.update(sysRole);

        return new ResponseResult<Void>(ResponseCode.OK.code(), ResponseCode.OK.message());
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @GetMapping(value = "delete/{id}")
    public ResponseResult<Void> delete(@PathVariable(value = "id") int id) {
        SysRole result = sysRoleService.removeById(id);

        // 删除所有用户角色的关联, 角色权限的关联
        int userRoles = sysUserRoleService.removeByRoleId(id);
        int RolePermission = sysRolePermissionService.removeByRoleId(id);
        // TODO 删除角色的子角色及 所有用户角色的关联, 角色权限的关联, 级联删除, 从下往上

        return new ResponseResult<Void>(ResponseCode.OK.code(), ResponseCode.OK.message());
    }
}
