package com.iscolt.micm.provider.api;

import com.iscolt.micm.commons.base.services.CrudService;
import com.iscolt.micm.commons.provider.view.UserPracticeView;
import com.iscolt.micm.provider.entity.ServiceUserPractice;

import java.util.List;

/**
 * xx
 * <p>
 * Description: TODO (Integer userId, Integer tenantId, long practiceId) 可以做成一个对象
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/10
 * @see: com.iscolt.micm.provider.api
 * @version: v1.0.0
 */
public interface ServiceUserPracticeService extends CrudService<ServiceUserPractice, Long> {

    /**
     * 查询用户报名的活动
     * @param userId
     * @param tenantId
     * @return
     */
    List<UserPracticeView> selectByUserIdAndTenantId(Integer userId, Integer tenantId) throws Exception;

    /**
     * 统计用户活动次数
     * @param userId
     * @param tenantId
     * @return
     */
    int countUserPracticeNum(Integer userId, Integer tenantId);

    /**
     * 统计用户活动积分
     * @param userId
     * @param tenantId
     * @return
     */
    int sumUserPracticeIntegral(Integer userId, Integer tenantId);

    /**
     * 活动报名
     * @param userId
     * @param tenantId
     * @param practiceId
     * @return
     */
    Boolean apply(Integer userId, Integer tenantId, long practiceId);

    /**
     * 取消报名
     * @param userId
     * @param tenantId
     * @param practiceId
     * @return
     */
    Boolean notApply(Integer userId, Integer tenantId, long practiceId);

    /**
     * 判断是否报名
     * @param userId
     * @param tenantId
     * @param practiceId
     * @return
     */
    Boolean judge(Integer userId, Integer tenantId, long practiceId);

    /**
     * 签退
     * @return
     */
    String checkOut(Integer userId, Integer tenantId, long practiceId);

    /**
     * 签到
     * @return
     */
    String checkIn(Integer userId, Integer tenantId, long practiceId);

    /**
     * 通过用户id，租户id，活动id
     *   用户查重， 是否重复报名
     * @param userId
     * @param tenantId
     * @param practiceId
     * @return
     */
    ServiceUserPractice getBy(Integer userId, Integer tenantId, long practiceId);
}
