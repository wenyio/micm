package com.iscolt.micm.provider.tests;

import com.iscolt.micm.commons.utils.IdWorker;
import com.iscolt.micm.provider.api.ServicePracticeOrganizationService;
import com.iscolt.micm.provider.entity.ServicePracticeOrganization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

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
public class ServicePracticeOrganizationTests {

    @Resource
    private IdWorker idWorker;

    @Resource
    private ServicePracticeOrganizationService servicePracticeOrganizationService;

    @Test
    public void getTree() throws Exception {
        System.out.println(servicePracticeOrganizationService.selectTreeByTenantId(1));
    }

    @Test
    public void add() {
//        ServicePracticeOrganization servicePracticeOrganization = new ServicePracticeOrganization();
//        servicePracticeOrganization.setId(idWorker.nextId());
//        servicePracticeOrganization.setParentId(0);
//        servicePracticeOrganization.setTenantId(1);
//        servicePracticeOrganization.setName("计算机与软件学院");
//        ServicePracticeOrganization servicePracticeOrganization1 = new ServicePracticeOrganization();
//        servicePracticeOrganization1.setId(idWorker.nextId());
//        servicePracticeOrganization1.setParentId(0);
//        servicePracticeOrganization1.setTenantId(1);
//        servicePracticeOrganization1.setName("计算机协会");
//        ServicePracticeOrganization servicePracticeOrganization2 = new ServicePracticeOrganization();
//        servicePracticeOrganization2.setId(idWorker.nextId());
//        servicePracticeOrganization2.setParentId(0);
//        servicePracticeOrganization2.setTenantId(1);
//        servicePracticeOrganization2.setName("航空航天学院");
//        ServicePracticeOrganization servicePracticeOrganization3 = new ServicePracticeOrganization();
//        servicePracticeOrganization3.setId(idWorker.nextId());
//        servicePracticeOrganization3.setParentId(0);
//        servicePracticeOrganization3.setTenantId(1);
//        servicePracticeOrganization3.setName("航拍俱乐部");
//        ServicePracticeOrganization servicePracticeOrganization4 = new ServicePracticeOrganization();
//        servicePracticeOrganization4.setId(idWorker.nextId());
//        servicePracticeOrganization4.setParentId(0);
//        servicePracticeOrganization4.setTenantId(1);
//        servicePracticeOrganization4.setName("电子工程学院");
//        ServicePracticeOrganization servicePracticeOrganization5 = new ServicePracticeOrganization();
//        servicePracticeOrganization5.setId(idWorker.nextId());
//        servicePracticeOrganization5.setParentId(0);
//        servicePracticeOrganization5.setTenantId(1);
//        servicePracticeOrganization5.setName("机器人俱乐部");
//        ServicePracticeOrganization servicePracticeOrganization6 = new ServicePracticeOrganization();
//        servicePracticeOrganization6.setId(idWorker.nextId());
//        servicePracticeOrganization6.setParentId(0);
//        servicePracticeOrganization6.setTenantId(1);
//        servicePracticeOrganization6.setName("党校-南工院分校");
//        ServicePracticeOrganization servicePracticeOrganization7 = new ServicePracticeOrganization();
//        servicePracticeOrganization7.setId(idWorker.nextId());
//        servicePracticeOrganization7.setParentId(0);
//        servicePracticeOrganization7.setTenantId(1);
//        servicePracticeOrganization7.setName("其他");
//        servicePracticeOrganizationService.create(servicePracticeOrganization);
//        servicePracticeOrganizationService.create(servicePracticeOrganization1);
//        servicePracticeOrganizationService.create(servicePracticeOrganization2);
//        servicePracticeOrganizationService.create(servicePracticeOrganization3);
//        servicePracticeOrganizationService.create(servicePracticeOrganization4);
//        servicePracticeOrganizationService.create(servicePracticeOrganization5);
//        servicePracticeOrganizationService.create(servicePracticeOrganization6);
//        servicePracticeOrganizationService.create(servicePracticeOrganization7);
    }
}
