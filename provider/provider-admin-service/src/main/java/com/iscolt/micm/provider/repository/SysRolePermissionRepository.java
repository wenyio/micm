package com.iscolt.micm.provider.repository;

import com.iscolt.micm.commons.base.repository.BaseRepository;
import com.iscolt.micm.provider.entity.SysRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * 角色权限
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/3
 * @see: com.iscolt.micm.provider.repository
 * @version: v1.0.0
 */
@Transactional
public interface SysRolePermissionRepository extends BaseRepository<SysRolePermission, Integer>, JpaRepository<SysRolePermission, Integer> {

    /**
     * 根据角色id删除
     * @param roleId
     * @return
     */
    int deleteByRoleId(int roleId);

    /**
     * 根据权限id删除
     * @param permissionId
     * @return
     */
    int deleteByPermissionId(int permissionId);

    /**
     * 通过角色id和权限id查询, 用于查重
     * @param roleId
     * @param permissionId
     * @return
     */
    SysRolePermission findByRoleIdAndPermissionId(int roleId, int permissionId);
}
