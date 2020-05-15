package com.iscolt.micm.business.controller;

import com.iscolt.micm.business.dto.params.UserRoleParam;
import com.iscolt.micm.commons.dto.ResponseResult;
import com.iscolt.micm.commons.model.dto.ResponseCode;
import com.iscolt.micm.provider.api.SysUserRoleService;
import com.iscolt.micm.provider.entity.SysUserRole;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * xx
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
@RequestMapping(value = "admin/user/role")
public class UserRoleController {

    @Reference(version = "1.0.0")
    private SysUserRoleService sysUserRoleService;

    /**
     * 用户添加角色
     * @param userRoleParam
     * @return
     */
    @PostMapping(value = "add")
    public ResponseResult<Void> add(@RequestBody UserRoleParam userRoleParam) {
        SysUserRole sysUserRole = new SysUserRole();
        BeanUtils.copyProperties(userRoleParam, sysUserRole);
        // 验证是否已经存在
        SysUserRole repeat = sysUserRoleService.get(sysUserRole.getUserId(), sysUserRole.getRoleId());
        if (repeat != null) {
            return new ResponseResult<Void>(ResponseCode.FAIL.code(), "此用户已经拥有该角色");
        }
        sysUserRoleService.create(sysUserRole);
        return new ResponseResult<Void>(ResponseCode.OK.code(), ResponseCode.OK.message());
    }

    /**
     * 存在就删除, 不存在就添加
     * @param userRoleParam
     * @return
     */
    @PostMapping(value = "negate")
    public ResponseResult<Void> negate(@RequestBody UserRoleParam userRoleParam) {
        SysUserRole sysUserRole = new SysUserRole();
        BeanUtils.copyProperties(userRoleParam, sysUserRole);
        // 验证是否已经存在
        SysUserRole repeat = sysUserRoleService.get(sysUserRole.getUserId(), sysUserRole.getRoleId());
        if (repeat != null) {
            sysUserRoleService.remove(repeat);
            return new ResponseResult<Void>(ResponseCode.OK.code(), "为用户取消该角色");
        }
        sysUserRoleService.create(sysUserRole);
        return new ResponseResult<Void>(ResponseCode.OK.code(), "为用户添加该角色");
    }
}
