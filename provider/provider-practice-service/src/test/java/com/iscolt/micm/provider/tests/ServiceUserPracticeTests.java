package com.iscolt.micm.provider.tests;

import com.iscolt.micm.provider.api.ServiceUserPracticeService;
import com.iscolt.micm.provider.entity.ServiceUserPractice;
import com.iscolt.micm.provider.repository.ServiceUserPracticeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.math.BigInteger;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/12
 * @see: com.iscolt.micm.provider.tests
 * @version: v1.0.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ServiceUserPracticeTests {

    @Resource
    private ServiceUserPracticeService serviceUserPracticeService;

    @Resource
    private ServiceUserPracticeRepository serviceUserPracticeRepository;

    @Test
    public void selectByUserIdAndTenantIdTest() throws Exception {
        System.out.println(serviceUserPracticeService.selectByUserIdAndTenantId(1, 1));
    }

    @Test
    public void sumUserPracticeIntegralTest() {
        System.out.println(serviceUserPracticeRepository.sumUserPracticeIntegral(2, 1));
    }

    @Test
    public void countUserPracticeNumTest() {
        System.out.println(serviceUserPracticeRepository.countUserPracticeNum(1, 1));
    }

    @Test
    public void add() {
        ServiceUserPractice serviceUserPractice = serviceUserPracticeRepository.findById(123L).get();
//        ServiceUserPractice serviceUserPractice = new ServiceUserPractice();
//        serviceUserPractice.setId(1234);
//        serviceUserPractice.setUserId(1);
//        serviceUserPractice.setTenantId(1);
//        serviceUserPractice.setPracticeId(12);
//        serviceUserPractice.setStatus(0);
        serviceUserPractice.setStatus(1);
//        serviceUserPracticeRepository.save(serviceUserPractice);
//        serviceUserPracticeService.update(serviceUserPractice);
        serviceUserPracticeRepository.updateStatus(serviceUserPractice.getStatus(), serviceUserPractice.getId(), serviceUserPractice.getTenantId());
    }
}
