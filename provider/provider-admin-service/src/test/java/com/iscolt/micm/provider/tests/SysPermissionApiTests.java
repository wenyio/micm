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
 * @date: 4/10/2020
 * @see: com.iscolt.micm.provider.tests
 * @version: v1.0.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SysPermissionApiTests {

//    @Resource
//    private SysPermissionApiRepository sysPermissionApiRepository;
//
//    @Resource
//    private SysPermissionApiService sysPermissionApiService;
//
//    @Test
//    public void findAllRoleAndApiTest() throws Exception {
//        List<Object[]> objects = sysPermissionApiRepository.findAllRoleAndApi();
//        List<SysRoleApiView> sysRoleApiViews = castEntity(objects, SysRoleApiView.class);
//        sysRoleApiViews.forEach(sysRoleApiView -> {
//            System.out.println(sysRoleApiView.getEnname());
//        });
//    }
//
//    @Test
//    public void selectAllRoleAndApi() throws Exception {
//        List<SysRoleApiView> sysRoleApiViews = sysPermissionApiService.selectAllRoleAndApi();
//        sysRoleApiViews.forEach(sysRoleApiView -> {
//            System.out.println(sysRoleApiView.getEnname());
//        });
//    }
//
//    @Test
//    public void selectPermissionApiTreeByTenantIdTest() throws Exception {
//        List<SysPermissionApiTreeDTO> sysPermissionApiTreeDTOS = sysPermissionApiService.selectPermissionApiTreeByTenantId(0);
//        System.out.println(sysPermissionApiTreeDTOS);
//    }
//
//    //转换实体类
//    public static <T> List<T> castEntity(List<Object[]> list, Class<T> clazz) throws Exception {
//        List<T> returnList = new ArrayList<T>();
//        if(CollectionUtils.isEmpty(list)){
//            return returnList;
//        }
//        Object[] co = list.get(0);
//        Class[] c2 = new Class[co.length];
//        //确定构造方法
//        for (int i = 0; i < co.length; i++) {
//            if(co[i]!=null){
//                c2[i] = co[i].getClass();
//            }else {
//                c2[i]=String.class;
//            }
//        }
//        for (Object[] o : list) {
//            Constructor<T> constructor = clazz.getConstructor(c2);
//            returnList.add(constructor.newInstance(o));
//        }
//        return returnList;
//    }
}
