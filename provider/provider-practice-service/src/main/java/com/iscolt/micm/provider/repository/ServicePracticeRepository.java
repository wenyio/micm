package com.iscolt.micm.provider.repository;

import com.iscolt.micm.commons.base.repository.BaseRepository;
import com.iscolt.micm.provider.entity.ServicePractice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
public interface ServicePracticeRepository extends BaseRepository<ServicePractice, Long>, JpaRepository<ServicePractice, Long> {

    /**
     * 通过标题，描述模糊查询
     * @param title
     * @param description
     * @return
     */
    List<ServicePractice> findByTenantIdAndTitleLikeOrTenantIdAndDescriptionLike(Integer tenantId, String title, Integer tenantId2, String description);

    /**
     * 通过组织id和租户id随机读取三条数据
     *  TODO 首页轮播图，需要单独管理
     * @param organizationId
     * @param tenantId
     * @return
     */
    @Query(value = "SELECT * FROM service_practice  " +
            "where tenant_id = :tenantId " +
            "and organization_id = :organizationId " +
            "ORDER BY  RAND() LIMIT 3 "
            , nativeQuery = true)
    List<ServicePractice> randThree(@Param("organizationId") Long organizationId, @Param("tenantId") Integer tenantId);

    List<ServicePractice> findByTenantId(Integer tenantId);
}
