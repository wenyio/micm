package com.iscolt.micm.provider.tests;

import com.iscolt.micm.provider.api.UmsAdminService;
import com.iscolt.micm.provider.entity.UmsAdmin;
import com.iscolt.micm.provider.repository.UmsAdminRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.persistence.Table;
import java.util.List;

/**
 * xx
 *
 * @Author: https://github.com/isColt
 * @Date: 3/30/2020
 * @Description: com.iscolt.micm.provider.tests
 * @version: v1.0.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UmsAdminTests {

    @Autowired
    private UmsAdminRepository umsAdminRepository;

    @Resource
    private UmsAdminService umsAdminService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void testSelectAll() {
        List<UmsAdmin> umsAdmins = umsAdminRepository.findAll();
        umsAdmins.forEach(umsAdmin -> {
            System.out.println(umsAdmin);
        });
    }

    @Test
    public void testSave() {
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setAvatar("test");
        umsAdmin.setUsername("test3");
        umsAdmin.setPassword(passwordEncoder.encode("123456"));
        UmsAdmin umsAdmin1 = umsAdminService.insert(umsAdmin);
        System.out.println(umsAdmin1);
    }

    @Test
    public void testGet() {
//        System.out.println(umsAdminService.get("test3"));
        System.out.println(umsAdminService.getById(4));
    }
}
