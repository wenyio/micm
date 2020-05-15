package com.iscolt.micm.provider.api;

import com.iscolt.micm.commons.base.services.CrudService;
import com.iscolt.micm.provider.entity.SysRolePermission;

/**
 * 角色权限管理
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/3
 * @see: com.iscolt.micm.provider.api
 * @version: v1.0.0
 */
public interface SysRolePermissionService extends CrudService<SysRolePermission, Integer> {

    /**
     * 根据角色id删除
     * @param roleId
     * @return
     */
    int removeByRoleId(int roleId);

    /**
     * 根据权限id删除
     * @param permissionId
     * @return
     */
    int removeByPermissionId(int permissionId);

    /**
     * 根据角色id和权限id查询
     * @param roleId
     * @param permissionId
     * @return
     */
    SysRolePermission get(int roleId, int permissionId);
}
