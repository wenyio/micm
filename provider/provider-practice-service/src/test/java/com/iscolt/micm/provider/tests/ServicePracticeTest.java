package com.iscolt.micm.provider.tests;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.iscolt.micm.commons.utils.IdWorker;
import com.iscolt.micm.provider.api.ServicePracticeService;
import com.iscolt.micm.provider.entity.ServicePractice;
import com.iscolt.micm.provider.repository.ServicePracticeRepository;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
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
 * @date: 2020/5/9
 * @see: com.iscolt.micm.provider.tests
 * @version: v1.0.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ServicePracticeTest {

    @Resource
    private ServicePracticeService servicePracticeService;

    @Resource
    private ServicePracticeRepository servicePracticeRepository;

    @Resource
    private IdWorker idWorker;

    /**
     * 根据tenant_id 分布式存储，实现不同租户用不同的数据库
     */
    @Test
    public void InsertServicePracticeTest() {
        ServicePractice servicePractice = new ServicePractice();
        // 部分数据， 用于测试
        servicePractice.setId(idWorker.nextId());
        servicePractice.setTitle("校织网宣讲会");
        servicePractice.setIcon("https://avatars2.githubusercontent.com/t/3706260?s=280&v=4");
        servicePractice.setTenantId(1);
        servicePractice.setUserId(1);
        servicePracticeService.create(servicePractice);
    }

    @Test
    public void SelectAllTest() {
        List<ServicePractice> servicePractices = servicePracticeService.listAll();
        servicePractices.forEach(servicePractice -> {
            System.out.println(servicePractice.getId());
        });
    }

    @Test
    public void limit3() {
        System.out.println(servicePracticeRepository.randThree(0L, 2));
    }

    @Test
    public void search() {
        System.out.println(servicePracticeService.search(1, "1"));
    }

    @Test
    public void timeTest() {
        ServicePractice servicePractice = servicePracticeService.getById(1259357918290063360L);
        long begin = servicePractice.getBegin().getTime();
        long end = servicePractice.getEnd().getTime();
        Timestamp nowDate = new Timestamp(new Date().getTime());
        long now = nowDate.getTime();
        if ((begin-300000) <= now && now <= (begin+300000)) {
            System.out.println("签到操作");
        } else if((end-300000) <= now && now <= (end+300000)) {
            System.out.println("签退操作");
        }
    }
}
