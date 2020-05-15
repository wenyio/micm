package com.iscolt.micm.provider.api;

import com.iscolt.micm.commons.base.services.CrudService;
import com.iscolt.micm.commons.model.dto.ServicePracticeCategoryTreeDTO;
import com.iscolt.micm.provider.entity.ServicePracticeCategory;

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
public interface ServicePracticeCategoryService extends CrudService<ServicePracticeCategory, Integer> {

    /**
     * 获取活动分类树
     * @param tenantId
     * @return
     * @throws Exception
     */
    List<ServicePracticeCategoryTreeDTO> selectTreeByTenantId(int tenantId) throws Exception;

    /**
     * 获取全部
     * @return
     */
    List<ServicePracticeCategory> listByTenantId(int tenantId);
}
