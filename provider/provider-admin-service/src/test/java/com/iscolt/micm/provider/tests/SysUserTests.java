package com.iscolt.micm.provider.tests;

import com.iscolt.micm.provider.api.SysUserService;
import com.iscolt.micm.provider.repository.SysUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/20/2020
 * @see: com.iscolt.micm.provider.tests
 * @version: v1.0.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SysUserTests {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysUserRepository sysUserRepository;

    @Test
    public void findListAllTest() {
        System.out.println(sysUserRepository.findAll());
    }

}
