package com.iscolt.micm.provider.api;

import com.iscolt.micm.commons.base.services.CrudService;
import com.iscolt.micm.provider.entity.SysTenant;
import com.iscolt.micm.provider.entity.SysTenantService;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * 租户服务管理
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/15
 * @see: com.iscolt.micm.provider.api
 * @version: v1.0.0
 */
public interface SysTenantServiceService extends CrudService<SysTenantService, Integer> {

    /**
     * 查询租户的服务
     * @param tid
     * @return
     */
    List<SysTenantService> selectByTenantId(Integer tid);

    /**
     * 查询租户和对应的服务
     * @param sid
     * @param tid
     * @return
     */
    SysTenantService selectByServiceIdAndTenantId(Integer sid, Integer tid);
}
