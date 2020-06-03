package com.iscolt.micm.provider.tests;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/6/2020
 * @see: com.iscolt.micm.provider.tests
 * @version: v1.0.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SysPerimissionTests {

//    @Resource
//    private SysPermissionService sysPermissionService;
//
//    @Resource
//    private SysPermissionRepository sysPermissionRepository;
//
//    @Test
//    public void UserPermissionTest(){
//        System.out.println(sysPermissionService.selectByUserId(1));
//    }
//
//    @Test
//    public void SelectAll(){
//        System.out.println(sysPermissionService.selectAll());
//    }
//
////    sysPermissionRepository.findByRoleId(roleId)
//    @Test
//    public void findByRoleIdTest() {
//        System.out.println(sysPermissionRepository.findByRoleId(2));
//    }
//
//    @Test
//    public void PermissionArrayByRoleTest() throws Exception {
//        List<List<Integer>> allArray = new ArrayList<>();
//        // 获取角色下的所有权限
//        List<SysPermission> sysPermissions = sysPermissionRepository.findByRoleId(1);
//
//        sysPermissions.forEach(sysPermission -> {
//            // 权限数组 => [1-id, 2-id, 3-id, ...],
//            List<Integer> array = new ArrayList<>();
//            List<Integer> stack = new ArrayList<>(); // 一个栈
//            // 如果父权限id为0 => [1-id]
//            if (sysPermission.getParentId() == 0) {
//                array.add(sysPermission.getId());
//                allArray.add(array);
//            }
//
//            else {
//                stack.add(sysPermission.getId()); // 最低级入栈, 先进后出, 最低级最后出
//                SysPermission parent = new SysPermission();
//                parent = sysPermissionRepository.findById(sysPermission.getParentId()).get();
//
//                while (parent.getParentId() != 0) {
//                    stack.add(parent.getId());
//                    parent = sysPermissionRepository.findById(parent.getParentId()).get(); // 重值对象
//                }
//
//                stack.add(parent.getId());
//
//                Collections.reverse(stack); // 将栈反转
//                array = stack;
//                allArray.add(array);
//            }
//        });
//
//        System.out.println(allArray);
//    }
}
