package com.iscolt.micm.provider.tests;

import com.iscolt.micm.provider.api.SysRoleService;
import com.iscolt.micm.provider.entity.SysRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.beans.PropertyDescriptor;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/9/2020
 * @see: com.iscolt.micm.provider.tests
 * @version: v1.0.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SysRoleTests {

    @Resource
    private SysRoleService sysRoleService;

    @Test
    public void getTreeTest() throws Exception {
        System.out.println(sysRoleService.selectRoleTreeByTenantId(0));
    }

//    PropertyDescriptor parentId = BeanUtils.getPropertyDescriptor(item.getClass(), parentFieldName);
    @Test
    public void PropertyDescriptorTest() {
        SysRole sysRole = new SysRole();
        sysRole.setParentId(1);
        PropertyDescriptor parentId = BeanUtils.getPropertyDescriptor(sysRole.getClass(), "parentId");
    }

    @Test
    public void selectRoleByUserIdTest() {
        System.out.println(sysRoleService.selectRoleByUserId(1));
    }
}
