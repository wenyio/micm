package com.iscolt.micm.provider.repository;

import com.iscolt.micm.commons.base.repository.BaseRepository;
import com.iscolt.micm.provider.entity.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

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
public interface SysUserRepository extends BaseRepository<SysUser, Integer>, JpaRepository<SysUser, Integer> {

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    SysUser findByUsername(String username);

    /**
     * 根据邮箱查询
     * @param email
     * @return
     */
    SysUser findByEmail(String email);

    /**
     * 根据手机号查询
     * @param phone
     * @return
     */
    SysUser findByPhone(String phone);

    /**
     * 通过租户id，删除标记，分页排序 查询
     * @param tenantId
     * @param deleted
     * @param pageable
     * @return
     */
    Page<SysUser> findAllByTenantIdAndDeleted(Integer tenantId, Boolean deleted, Pageable pageable);
}
