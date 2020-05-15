package com.iscolt.micm.provider.repository;

import com.beust.jcommander.IValueValidator;
import com.iscolt.micm.commons.base.repository.BaseRepository;
import com.iscolt.micm.provider.entity.SysPermission;
import com.iscolt.micm.provider.entity.SysUser;
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
 * @date: 4/6/2020
 * @see: com.iscolt.micm.provider.repository
 * @version: v1.0.0
 */
@Transactional
public interface SysPermissionRepository extends BaseRepository<SysPermission, Integer>, JpaRepository<SysPermission, Integer> {

    @Query(value = "SELECT" +
            "  p.*" +
            " FROM" +
            "  sys_user AS u" +
            "  LEFT JOIN sys_user_role AS ur" +
            "    ON u.id = ur.user_id" +
            "  LEFT JOIN sys_role AS r" +
            "    ON r.id = ur.role_id" +
            "  LEFT JOIN sys_role_permission AS rp" +
            "    ON r.id = rp.role_id" +
            "  LEFT JOIN sys_permission AS p" +
            "    ON p.id = rp.permission_id" +
            " WHERE u.id = :uid", nativeQuery = true)
    List<SysPermission> findByUserId(@Param("uid") int uid);

    /**
     * 查询租户下的权限
     * @param tid
     * @return
     */
    List<SysPermission> findByTenantId(int tid);

    /**
     * 查询角色对应的权限
     * @param rid
     * @return
     */
    @Query(value = "SELECT p.* FROM sys_role AS r " +
            "LEFT JOIN sys_role_permission AS rp ON r.id = rp.role_id " +
            "LEFT JOIN sys_permission AS p ON p.id = rp.permission_id " +
            "WHERE r.id = :rid", nativeQuery = true)
    List<SysPermission> findByRoleId(int rid);
}
