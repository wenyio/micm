package com.iscolt.micm.provider.repository;

import com.iscolt.micm.commons.base.repository.BaseRepository;
import com.iscolt.micm.provider.entity.ServicePracticeOrganization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

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
public interface ServicePracticeOrganizationRepository extends BaseRepository<ServicePracticeOrganization, Integer>, JpaRepository<ServicePracticeOrganization, Integer> {

    /**
     * 根据租户查询
     * @param tid
     * @return
     */
    List<ServicePracticeOrganization> findByTenantId(int tid);
}
