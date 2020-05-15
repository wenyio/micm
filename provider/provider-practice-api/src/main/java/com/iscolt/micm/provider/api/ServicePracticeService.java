package com.iscolt.micm.provider.api;

import com.iscolt.micm.commons.base.services.CrudService;
import com.iscolt.micm.provider.entity.ServicePractice;

import java.util.List;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/9
 * @see: com.iscolt.micm.provider.api
 * @version: v1.0.0
 */
public interface ServicePracticeService extends CrudService<ServicePractice, Long> {

    /**
     * 搜索
     * @return
     */
    List<ServicePractice> search(Integer tenantId, String key);

    /**
     * 根据组织id推荐
     * @return
     */
    List<ServicePractice> recommend(Integer tenantId, long organizationId);

    /**
     * 通过租户id获取
     * @param tenantId
     * @return
     */
    List<ServicePractice> listByTenantId(int tenantId);
}
