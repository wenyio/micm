package com.iscolt.micm.provider.api;

import com.iscolt.micm.commons.base.services.CrudService;
import com.iscolt.micm.commons.model.dto.ServicePracticeOrganizationTreeDTO;
import com.iscolt.micm.provider.entity.ServicePracticeOrganization;

import java.util.List;

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
public interface ServicePracticeOrganizationService extends CrudService<ServicePracticeOrganization, Integer> {

    /**
     * 获取活动组织树
     * @param tenantId
     * @return
     * @throws Exception
     */
    List<ServicePracticeOrganizationTreeDTO> selectTreeByTenantId(int tenantId) throws Exception;

    /**
     * 根据租户id查询全部
     * @param tenantId
     * @return
     */
    List<ServicePracticeOrganization> listByTenantId(int tenantId);
}
