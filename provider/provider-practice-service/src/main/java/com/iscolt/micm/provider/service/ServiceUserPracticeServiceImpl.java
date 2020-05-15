package com.iscolt.micm.provider.service;

import com.iscolt.micm.commons.base.repository.BaseRepository;
import com.iscolt.micm.commons.base.services.AbstractCrudService;
import com.iscolt.micm.commons.constant.PracticeConstant;
import com.iscolt.micm.commons.provider.view.UserPracticeView;
import com.iscolt.micm.commons.utils.IdWorker;
import com.iscolt.micm.provider.api.ServiceUserPracticeService;
import com.iscolt.micm.provider.entity.ServiceUserPractice;
import com.iscolt.micm.provider.repository.ServiceUserPracticeRepository;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/10
 * @see: com.iscolt.micm.provider.service
 * @version: v1.0.0
 */
@Service(version = "1.0.0")
public class ServiceUserPracticeServiceImpl extends AbstractCrudService<ServiceUserPractice, Long> implements ServiceUserPracticeService {

    @Resource
    private ServiceUserPracticeRepository serviceUserPracticeRepository;

    @Resource
    private IdWorker idWorker;

    protected ServiceUserPracticeServiceImpl(BaseRepository<ServiceUserPractice, Long> repository) {
        super(repository);
    }

    @Override
    public List<UserPracticeView> selectByUserIdAndTenantId(Integer userId, Integer tenantId) throws Exception {
        List<Object[]> objects = serviceUserPracticeRepository.findByUserIdAndTenantId(userId, tenantId);

        List<UserPracticeView> userPracticeViews = castEntity(objects, UserPracticeView.class);

        return userPracticeViews;
    }

    @Override
    public int countUserPracticeNum(Integer userId, Integer tenantId) {
        return serviceUserPracticeRepository.countUserPracticeNum(userId, tenantId);
    }

    @Override
    public int sumUserPracticeIntegral(Integer userId, Integer tenantId) {
        return serviceUserPracticeRepository.sumUserPracticeIntegral(userId, tenantId);
    }

    @Override
    public Boolean apply(Integer userId, Integer tenantId, long practiceId) {
        if (judge(userId, tenantId, practiceId)) { // 如果已报名， 不能再次报名
            return false;
        }
        ServiceUserPractice serviceUserPractice = new ServiceUserPractice();
        serviceUserPractice.setId(idWorker.nextId());
        serviceUserPractice.setStatus(PracticeConstant.DID_NOT_PARTICIPATE.code()); // 初始未签到，未签退
        serviceUserPractice.setPracticeId(practiceId);
        serviceUserPractice.setTenantId(tenantId);
        serviceUserPractice.setUserId(userId);
        serviceUserPracticeRepository.save(serviceUserPractice); // 保存
        return true;
    }

    @Override
    public Boolean notApply(Integer userId, Integer tenantId, long practiceId) {
        if (judge(userId, tenantId, practiceId)) { // 已报名
            ServiceUserPractice serviceUserPractice = serviceUserPracticeRepository.findByUserIdAndTenantIdAndPracticeId(userId, tenantId, practiceId);
            serviceUserPracticeRepository.delete(serviceUserPractice);
            return true; // 成功取消报名
        }
        // 未报名，无法取消报名
        return false;
    }

    @Override
    public Boolean judge(Integer userId, Integer tenantId, long practiceId) {
        ServiceUserPractice serviceUserPractice = getBy(userId, tenantId, practiceId);
        // 不等于空说明 已报名返回true
        return serviceUserPractice != null;
    }

    @Override
    public String checkOut(Integer userId, Integer tenantId, long practiceId) {
        String message = "";
        ServiceUserPractice serviceUserPractice = getBy(userId, tenantId, practiceId);
        if (serviceUserPractice == null) {
            message = "你没有报名此活动";
            return message;
        }
        switch (serviceUserPractice.getStatus()) {
            case 11: // 已签到，以签退， break
                message = PracticeConstant.HAVE_PARTICIPATED.checkOutMsg();
                break;
            case 0: // 0 = 00 未签到，未签退
                serviceUserPracticeRepository.updateStatus(PracticeConstant.NOT_CHECKED_IN.code(), serviceUserPractice.getId(),serviceUserPractice.getTenantId());
                message = PracticeConstant.DID_NOT_PARTICIPATE.checkOutMsg();
                break;
            case 1: // 1 = 01 未签到，已签退， break(已签退， 不能再次签退)
                message = PracticeConstant.NOT_CHECKED_IN.checkOutMsg();
                break;
            case 10: // 10 已签到，未签退
                serviceUserPracticeRepository.updateStatus(PracticeConstant.HAVE_PARTICIPATED.code(), serviceUserPractice.getId(),serviceUserPractice.getTenantId());
                message = PracticeConstant.NOT_CHECKED_OUT.checkOutMsg();
                break;
            default: // 状态未知，设为 未签到，未签退
                serviceUserPracticeRepository.updateStatus(PracticeConstant.DID_NOT_PARTICIPATE.code(), serviceUserPractice.getId(),serviceUserPractice.getTenantId());
                message = PracticeConstant.NOT_FOUND.checkOutMsg();
                break;
        }
        return message;
    }

    @Override
    public String checkIn(Integer userId, Integer tenantId, long practiceId) {
        String message = "";
        ServiceUserPractice serviceUserPractice = getBy(userId, tenantId, practiceId);
        if (serviceUserPractice == null) {
            message = "你没有报名此活动";
            return message;
        }
        switch (serviceUserPractice.getStatus()) {
            case 11: // 已签到，以签退， break
                message = PracticeConstant.HAVE_PARTICIPATED.checkInMsg();
                break;
            case 0: // 0 = 00 未签到，未签退
                serviceUserPracticeRepository.updateStatus(PracticeConstant.NOT_CHECKED_OUT.code(), serviceUserPractice.getId(),serviceUserPractice.getTenantId());
                message = PracticeConstant.DID_NOT_PARTICIPATE.checkInMsg();
                break;
            case 1:  // 1 = 01 未签到，已签退， break(都已经签退了， 不能签到)
                message = PracticeConstant.NOT_CHECKED_IN.checkInMsg();
                break;
            case 10: // 10 已签到，未签退, break(不可以再次签到)
                message = PracticeConstant.NOT_CHECKED_OUT.checkInMsg();
                break;
            default: // 状态未知，设为 未签到，未签退
                serviceUserPracticeRepository.updateStatus(PracticeConstant.DID_NOT_PARTICIPATE.code(), serviceUserPractice.getId(),serviceUserPractice.getTenantId());
                message = PracticeConstant.NOT_FOUND.checkInMsg();
                break;
        }
        return message;
    }

    @Override
    public ServiceUserPractice getBy(Integer userId, Integer tenantId, long practiceId) {
        return serviceUserPracticeRepository.findByUserIdAndTenantIdAndPracticeId(userId, tenantId, practiceId);
    }

    /**
     * 转换实体类
     * @param list
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> castEntity(List<Object[]> list, Class<T> clazz) throws Exception {
        List<T> returnList = new ArrayList<T>();
        if(CollectionUtils.isEmpty(list)){
            return returnList;
        }
        Object[] co = list.get(0);
        Class[] c2 = new Class[co.length];
        //确定构造方法
        for (int i = 0; i < co.length; i++) {
            if(co[i]!=null){
                c2[i] = co[i].getClass();
            }else {
                c2[i]=String.class;
            }
        }
        for (Object[] o : list) {
            Constructor<T> constructor = clazz.getConstructor(c2);
            returnList.add(constructor.newInstance(o));
        }
        return returnList;
    }
}
