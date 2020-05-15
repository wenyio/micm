package com.iscolt.micm.provider.repository;

import com.iscolt.micm.commons.base.repository.BaseRepository;
import com.iscolt.micm.provider.entity.SysPermissionApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/9/2020
 * @see: com.iscolt.micm.provider.repository
 * @version: v1.0.0
 */
@Transactional
public interface SysPermissionApiRepository extends BaseRepository<SysPermissionApi, Integer>, JpaRepository<SysPermissionApi, Integer> {

    /**
     * 查询所有API及对应的角色
     * @return
     */
    @Query(value = "SELECT p.id AS id, r.enname, pa.url AS url FROM sys_permission_api As pa  " +
            "LEFT JOIN sys_permission AS p ON p.id = pa.permission_id " +
            "LEFT JOIN sys_role_permission AS rp ON p.id = rp.permission_id " +
            "LEFT JOIN sys_role AS r ON r.id = rp.role_id " +
            "WHERE  p.type = 1 AND p.deleted = 1 AND r.enname IS NOT NULL",
            nativeQuery = true)
    List<Object[]> findAllRoleAndApi();

    /**
     * 根据租户id查询
     * @param tid
     * @return
     */
    @Query(value = "SELECT p.id, p.parent_id, p.tenant_id, p.name, p.description, pa.url AS url FROM sys_permission_api As pa  " +
            "LEFT JOIN sys_permission AS p ON p.id = pa.permission_id " +
            "WHERE  p.type = 1 AND p.deleted = 1 AND p.tenant_id = :tid", nativeQuery = true)
    List<Object[]> findAllByTenantId(@Param("tid") int tid);

    /**
     * 根据权限id查询
     * @param permissionId
     * @return
     */
    SysPermissionApi findByPermissionId(int permissionId);

    /**
     * 根据权限id删除
     * @param permissionId
     * @return
     */
    int deleteByPermissionId(int permissionId);
}
