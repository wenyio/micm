package com.iscolt.micm.provider.api;

import com.iscolt.micm.commons.base.services.CrudService;
import com.iscolt.micm.commons.model.dto.SysPermissionTreeDTO;
import com.iscolt.micm.provider.entity.SysPermission;
import com.iscolt.micm.provider.entity.SysUser;

import java.util.List;

/**
 * 权限管理
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/6/2020
 * @see: com.iscolt.micm.provider.api
 * @version: v1.0.0
 */
public interface SysPermissionService extends CrudService<SysPermission, Integer> {

    /**
     * 通过用户id查询用户权限
     * @param uid
     * @return
     */
    List<SysPermission> selectByUserId(int uid);

    /**
     * 查询有所权限
     * @return
     */
    List<SysPermission> selectAll();

    /**
     * 获取对应组织下的权限树
     * @return
     */
    List<SysPermissionTreeDTO> selectPermissionTreeByTenantId(int tenantId) throws Exception;

    /**
     * 权限与权限的父id组装, 格式如下:
     *  [
     *      [1-id, 2-id, 3-id, ...],
     *      [1-id, 2-id, ...],
     *      [1-id, ...],
     *      [1-id],
     *  ]
     * @param roleId
     * @return
     */
    List<List<Integer>> selectPermissionArrayByRoleId(int roleId);
}
