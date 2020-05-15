package com.iscolt.micm.business.controller;

import com.iscolt.micm.commons.dto.ResponseResult;
import com.iscolt.micm.commons.model.dto.ResponseCode;
import com.iscolt.micm.commons.model.dto.SysPermissionTreeDTO;
import com.iscolt.micm.provider.api.SysPermissionApiService;
import com.iscolt.micm.provider.api.SysPermissionService;
import com.iscolt.micm.provider.api.SysUserService;
import com.iscolt.micm.provider.entity.SysUser;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 权限控制器
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
@RequestMapping(value = "admin/permissions")
public class PermissionController {

    @Reference(version = "1.0.0")
    private SysUserService sysUserService;

    @Reference(version = "1.0.0")
    private SysPermissionService sysPermissionService;

    /**
     * 判断权限和组织ID获取所能管理的权限
     * @return
     */
    @GetMapping(value = "")
    public ResponseResult<List<SysPermissionTreeDTO>> list() throws Exception {
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = sysUserService.get(authentication.getName());
        List<SysPermissionTreeDTO> sysPermissionTreeDTOS = sysPermissionService.selectPermissionTreeByTenantId(sysUser.getTenantId());
        return new ResponseResult<List<SysPermissionTreeDTO>>(ResponseCode.OK.code(), "查询成功", sysPermissionTreeDTOS);
    }

    /**
     * 获取角色对应的权限
     * @param roleId
     * @return
     * @throws Exception
     */
    @GetMapping(value = "role/{roleId}/array")
    public ResponseResult<List<List<Integer>>> getArrayByRoleId(@PathVariable(value = "roleId") int roleId) throws Exception {
        List<List<Integer>> array = sysPermissionService.selectPermissionArrayByRoleId(roleId);
        return new ResponseResult<List<List<Integer>>>(ResponseCode.OK.code(), "查询成功", array);
    }
}
