package com.iscolt.micm.provider.repository;

import com.iscolt.micm.commons.base.repository.BaseRepository;
import com.iscolt.micm.provider.entity.TenantUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/9
 * @see: com.iscolt.micm.provider.repository
 * @version: v1.0.0
 */
public interface TenantUserInfoRepository extends BaseRepository<TenantUserInfo, Integer>, JpaRepository<TenantUserInfo, Integer> {

    /**
     * 通过用户id获取详情信息
     * @param userId
     * @return
     */
    TenantUserInfo findByUserId(int userId);
}
