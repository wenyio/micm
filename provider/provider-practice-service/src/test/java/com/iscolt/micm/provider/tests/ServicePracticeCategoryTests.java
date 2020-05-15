package com.iscolt.micm.provider.tests;

import com.iscolt.micm.commons.utils.IdWorker;
import com.iscolt.micm.provider.api.ServicePracticeCategoryService;
import com.iscolt.micm.provider.api.ServicePracticeService;
import com.iscolt.micm.provider.entity.ServicePracticeCategory;
import com.iscolt.micm.provider.service.ServicePracticeCategoryServiceImpl;
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
public class ServicePracticeCategoryTests {

    @Resource
    private IdWorker idWorker;

    @Resource
    private ServicePracticeCategoryService servicePracticeCategoryService;

    @Test
    public void getTree() throws Exception {
        System.out.println(servicePracticeCategoryService.selectTreeByTenantId(1));
    }

    @Test
    public void add() {
//        ServicePracticeCategory servicePracticeCategory = new ServicePracticeCategory();
//        servicePracticeCategory.setId(idWorker.nextId());
//        servicePracticeCategory.setParentId(0);
//        servicePracticeCategory.setTenantId(1);
//        servicePracticeCategory.setName("讲座"); // 普通讲座 其他讲座 活动 志愿活动 实践活动 其他活动
//        ServicePracticeCategory servicePracticeCategory1 = new ServicePracticeCategory();
//        servicePracticeCategory1.setId(idWorker.nextId());
//        servicePracticeCategory1.setParentId(0);
//        servicePracticeCategory1.setTenantId(1);
//        servicePracticeCategory1.setName("普通讲座"); // 普通讲座 其他讲座 活动 志愿活动 实践活动 其他活动
//        ServicePracticeCategory servicePracticeCategory2 = new ServicePracticeCategory();
//        servicePracticeCategory2.setId(idWorker.nextId());
//        servicePracticeCategory2.setParentId(0);
//        servicePracticeCategory2.setTenantId(1);
//        servicePracticeCategory2.setName("其他讲座"); // 普通讲座 其他讲座 活动 志愿活动 实践活动 其他活动
//        ServicePracticeCategory servicePracticeCategory3 = new ServicePracticeCategory();
//        servicePracticeCategory3.setId(idWorker.nextId());
//        servicePracticeCategory3.setParentId(0);
//        servicePracticeCategory3.setTenantId(1);
//        servicePracticeCategory3.setName("活动"); // 普通讲座 其他讲座 活动 志愿活动 实践活动 其他活动
//        ServicePracticeCategory servicePracticeCategory4 = new ServicePracticeCategory();
//        servicePracticeCategory4.setId(idWorker.nextId());
//        servicePracticeCategory4.setParentId(0);
//        servicePracticeCategory4.setTenantId(1);
//        servicePracticeCategory4.setName("志愿活动"); // 普通讲座 其他讲座 活动 志愿活动 实践活动 其他活动
//        ServicePracticeCategory servicePracticeCategory5 = new ServicePracticeCategory();
//        servicePracticeCategory5.setId(idWorker.nextId());
//        servicePracticeCategory5.setParentId(0);
//        servicePracticeCategory5.setTenantId(1);
//        servicePracticeCategory5.setName("实践活动"); // 普通讲座 其他讲座 活动 志愿活动 实践活动 其他活动
//        ServicePracticeCategory servicePracticeCategory6 = new ServicePracticeCategory();
//        servicePracticeCategory6.setId(idWorker.nextId());
//        servicePracticeCategory6.setParentId(0);
//        servicePracticeCategory6.setTenantId(1);
//        servicePracticeCategory6.setName("其他活动"); // 普通讲座 其他讲座 活动 志愿活动 实践活动 其他活动
//
//        servicePracticeCategoryService.create(servicePracticeCategory);
//        servicePracticeCategoryService.create(servicePracticeCategory1);
//        servicePracticeCategoryService.create(servicePracticeCategory2);
//        servicePracticeCategoryService.create(servicePracticeCategory3);
//        servicePracticeCategoryService.create(servicePracticeCategory4);
//        servicePracticeCategoryService.create(servicePracticeCategory5);
//        servicePracticeCategoryService.create(servicePracticeCategory6);
    }
}
