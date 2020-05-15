package com.iscolt.micm.business.controller;

import com.iscolt.micm.business.dto.params.RolePermissionListParam;
import com.iscolt.micm.business.dto.params.RolePermissionParam;
import com.iscolt.micm.commons.dto.ResponseResult;
import com.iscolt.micm.commons.model.dto.ResponseCode;
import com.iscolt.micm.provider.api.SysRolePermissionService;
import com.iscolt.micm.provider.entity.SysRolePermission;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色权限管理
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/3
 * @see: com.iscolt.micm.business.controller
 * @version: v1.0.0
 */
@RestController
@RequestMapping(value = "admin/role/permission")
public class RolePermissionController {

    @Reference(version = "1.0.0")
    private SysRolePermissionService sysRolePermissionService;

    @PostMapping(value = "add")
    public ResponseResult<Void> add(@RequestBody RolePermissionParam rolePermissionParam) {
        SysRolePermission sysRolePermission = new SysRolePermission();
        BeanUtils.copyProperties(rolePermissionParam, sysRolePermission);
        // 验证是否存在
        SysRolePermission repeat = sysRolePermissionService.get(sysRolePermission.getRoleId(), sysRolePermission.getPermissionId());
        if (repeat != null) {
            return new ResponseResult<Void>(ResponseCode.FAIL.code(), "此角色已经拥有该权限");
        }
        SysRolePermission result = sysRolePermissionService.create(sysRolePermission);
        return new ResponseResult<Void>(ResponseCode.OK.code(), ResponseCode.OK.message());
    }

    /**
     * 批量添加权限
     *  1. 删除之前的所有权限
     *  2. 批量创建
     *  3. 数据持久层添加事务回滚, 防止出错
     *
     *  TODO 业务逻辑需要完善
     * @param rolePermissionListParam
     * @return
     */
    @PostMapping(value = "addList")
    public ResponseResult<Void> addList(@RequestBody RolePermissionListParam rolePermissionListParam) {
        List<SysRolePermission> sysRolePermissions = new ArrayList<>();
        Integer roleId = rolePermissionListParam.getRoleId();
        rolePermissionListParam.getList().forEach(pid -> {
            SysRolePermission sysRolePermission = new SysRolePermission();
            sysRolePermission.setRoleId(roleId);
            sysRolePermission.setPermissionId(pid);
            sysRolePermissions.add(sysRolePermission);
        });

        int result = sysRolePermissionService.removeByRoleId(roleId);// 先批量删除
        sysRolePermissionService.createInBatch(sysRolePermissions); // 批量创建权限

        return new ResponseResult<Void>(ResponseCode.OK.code(), "删除了" +
                result +"条数据, 新增" +
                rolePermissionListParam.getList().size() + "条数据~");
    }
}
