package com.iscolt.micm.provider.repository;

import com.iscolt.micm.commons.base.repository.BaseRepository;
import com.iscolt.micm.provider.entity.SysRole;
import com.iscolt.micm.provider.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 系统角色持久层
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
public interface SysRoleRepository extends BaseRepository<SysRole, Integer>, JpaRepository<SysRole, Integer> {

    /**
     * 查询用户角色
     * @param uid
     * @return
     */
    @Query(value = "SELECT r.* FROM sys_user As u " +
            "LEFT JOIN sys_user_role AS ur ON u.id = ur.user_id " +
            "LEFT JOIN sys_role As r ON r.id = ur.role_id " +
            "WHERE u.id = :uid AND r.deleted = 1", nativeQuery = true)
    List<SysRole> findByUserId(@Param("uid") int uid);

    /**
     * 查询租户下的角色树
     * @param tid
     * @return
     */
    List<SysRole> findByTenantId(int tid);

    /**
     * 根据父id删除
     * @param parentId
     * @return
     */
    List<SysRole> deleteByParentId(int parentId);

}
