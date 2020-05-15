package com.iscolt.micm.provider.api;

import com.iscolt.micm.commons.base.services.CrudService;
import com.iscolt.micm.provider.entity.TenantUserInfo;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/10
 * @see: com.iscolt.micm.provider.api
 * @version: v1.0.0
 */
public interface TenantUserInfoService extends CrudService<TenantUserInfo, Integer> {

    /**
     * 通过用户id获取详情信息
     * @param userId
     * @return
     */
    TenantUserInfo getByUserId(int userId);
}
