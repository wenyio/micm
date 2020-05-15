package com.iscolt.micm.provider.repository;

import com.iscolt.micm.commons.base.repository.BaseRepository;
import com.iscolt.micm.provider.entity.ServiceUserPractice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.math.BigInteger;
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
@Transactional
public interface ServiceUserPracticeRepository extends BaseRepository<ServiceUserPractice, Long>, JpaRepository<ServiceUserPractice, Long> {

    @Query(value = "select up.id, up.status, up.created, up.practice_id, p.title, p.icon, p.integral, pc.name as category_name " +
            "from service_user_practice as up " +
            "left join service_practice as p on up.practice_id = p.id " +
            "left join service_practice_category as pc on p.category_id = pc.id " +
            "where up.user_id = :uid and up.tenant_id = :tid and p.tenant_id = :tid and pc.tenant_id = :tid"
            , nativeQuery = true)
    List<Object[]> findByUserIdAndTenantId(@Param("uid") int userId, @Param("tid") int tenantId);

    /**
     * 统计用户活动积分
     * @param userId
     * @param tenantId
     * @return
     */
    @Query(value = "select IFNULL(sum(p.integral),0) as integral " +
            "from service_user_practice as up " +
            "left join service_practice as p on up.practice_id = p.id " +
            "where up.user_id = :uid and up.tenant_id = :tid and p.tenant_id = :tid and up.status = 11"
            , nativeQuery = true)
    int sumUserPracticeIntegral(@Param("uid") int userId, @Param("tid") int tenantId);

    /**
     * 统计用户活动次数
     * @param userId
     * @param tenantId
     * @return
     */
    @Query(value = "select IFNULL(count(p.integral),0) as count " +
            "from service_user_practice as up " +
            "left join service_practice as p on up.practice_id = p.id " +
            "where up.user_id = :uid and up.tenant_id = :tid and p.tenant_id = :tid"
            , nativeQuery = true)
    int countUserPracticeNum(@Param("uid") int userId, @Param("tid") int tenantId);

    /**
     * 通过用户id 租户id 活动id 查询
     * @param userId
     * @param tenantId
     * @param practiceId
     * @return
     */
    ServiceUserPractice findByUserIdAndTenantIdAndPracticeId(int userId, int tenantId, long practiceId);

    /**
     * 更新用户活动的状态
     * @param status
     * @param id
     * @param tid
     * @return
     */
    @Modifying(clearAutomatically = true)
    @Query(value="update service_user_practice set status=:status where id=:id and tenant_id = :tid", nativeQuery = true)
    int updateStatus(@Param("status") Integer status, @Param("id") Long id, @Param("tid") Integer tid);
}
