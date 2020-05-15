package com.iscolt.micm.provider.repository;

import com.iscolt.micm.commons.base.repository.BaseRepository;
import com.iscolt.micm.provider.entity.SysUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 用户权限
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
public interface SysUserRoleRepository extends BaseRepository<SysUserRole, Integer>, JpaRepository<SysUserRole, Integer> {

    /**
     * 根据角色id删除
     * @param roleId
     * @return
     */
    int deleteByRoleId(int roleId);

    /**
     * 根据用户id删除
     * @param userId
     * @return
     */
    int deleteByUserId(int userId);

    /**
     * 根据用户和角色删除
     * @param userId
     * @param roleId
     * @return
     */
    int deleteByUserIdAndRoleId(int userId, int roleId);

    /**
     * 更具用户和角色查询, 用于查重
     * @param userId
     * @param roleId
     * @return
     */
    SysUserRole findByUserIdAndRoleId(int userId, int roleId);
}
