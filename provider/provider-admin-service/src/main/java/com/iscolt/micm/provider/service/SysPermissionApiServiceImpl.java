package com.iscolt.micm.provider.service;

import com.iscolt.micm.commons.base.repository.BaseRepository;
import com.iscolt.micm.commons.base.services.AbstractCrudService;
import com.iscolt.micm.commons.model.dto.SysPermissionApiTreeDTO;
import com.iscolt.micm.commons.model.dto.SysRoleTreeDTO;
import com.iscolt.micm.commons.provider.view.SysPermissionApiView;
import com.iscolt.micm.commons.provider.view.SysRoleApiView;
import com.iscolt.micm.provider.api.SysPermissionApiService;
import com.iscolt.micm.provider.entity.SysPermissionApi;
import com.iscolt.micm.provider.repository.SysPermissionApiRepository;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
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
 * @date: 4/9/2020
 * @see: com.iscolt.micm.provider.service
 * @version: v1.0.0
 */
@Service(version = "1.0.0")
public class SysPermissionApiServiceImpl extends AbstractCrudService<SysPermissionApi, Integer> implements SysPermissionApiService {

    @Resource
    private SysPermissionApiRepository sysPermissionApiRepository;

    protected SysPermissionApiServiceImpl(BaseRepository<SysPermissionApi, Integer> repository) {
        super(repository);
    }

    @Override
    public List<SysPermissionApi> selectAll() {
        return sysPermissionApiRepository.findAll();
    }

    @Override
    public List<SysRoleApiView> selectAllRoleAndApi() throws Exception {
        List<Object[]> objects = sysPermissionApiRepository.findAllRoleAndApi();

        List<SysRoleApiView> sysRoleApiViews = castEntity(objects, SysRoleApiView.class);

        return sysRoleApiViews;
    }

    @Override
    public List<SysPermissionApiTreeDTO> selectPermissionApiTreeByTenantId(int tenantId) throws Exception {
        List<Object[]> objects = sysPermissionApiRepository.findAllByTenantId(tenantId);
        List<SysPermissionApiView> sysPermissionApiViews = castEntity(objects, SysPermissionApiView.class);
        List<SysPermissionApiTreeDTO> sysPermissionApiTreeDTOS = new ArrayList<>();

        sysPermissionApiViews.forEach(sysPermissionApiView -> {
            SysPermissionApiTreeDTO sysPermissionApiTreeDTO = new SysPermissionApiTreeDTO();
            BeanUtils.copyProperties(sysPermissionApiView, sysPermissionApiTreeDTO);
            sysPermissionApiTreeDTOS.add(sysPermissionApiTreeDTO);
        });

        return getTree(sysPermissionApiTreeDTOS);
    }

    @Override
    public SysPermissionApi getByPermissionId(int permissionId) {
        return sysPermissionApiRepository.findByPermissionId(permissionId);
    }

    @Override
    public int removeByPermissionId(int permissionId) {
        return sysPermissionApiRepository.deleteByPermissionId(permissionId);
    }

    /**
     * 把列表转换为树结构
     *
     * @param originalList 原始list数据
     * @return 组装后的集合
     */
    public static List<SysPermissionApiTreeDTO> getTree(List<SysPermissionApiTreeDTO> originalList) throws Exception {

        // 获取根节点，即找出父节点为空的对象
        List<SysPermissionApiTreeDTO> topList = new ArrayList<>();
        originalList.forEach(item -> {
            if (item.getParentId() == 0) { // TODO 父节点id做成常量
                topList.add(item);
            }
        });

        // 将根节点从原始list移除，减少下次处理数据
        originalList.removeAll(topList);

        // 递归封装树
        fillTree(topList, originalList);

        return topList;
    }

    /**
     * 封装树
     *
     * @param parentList 要封装为树的父对象集合
     * @param originalList 原始list数据
     */
    public static List<SysPermissionApiTreeDTO> fillTree(List<SysPermissionApiTreeDTO> parentList, List<SysPermissionApiTreeDTO> originalList) throws Exception {
        parentList.forEach(parent -> {
            List<SysPermissionApiTreeDTO> children = null;
            try {
                // 获取子节点
                children = fillChildren(parent, originalList);
                parent.setChildren(children);
            } catch (Exception e) {
                e.printStackTrace();
            }
            originalList.removeAll(children);
        });
        return parentList;
    }

    /**
     * 封装子对象
     *
     * @param parent 父对象
     * @param originalList 待处理对象集合
     */
    public static List<SysPermissionApiTreeDTO> fillChildren(SysPermissionApiTreeDTO parent, List<SysPermissionApiTreeDTO> originalList) throws Exception {
        List<SysPermissionApiTreeDTO> childList = new ArrayList<>();
        int parentId = parent.getId();
        // 一次循环, 获取子节点
        originalList.forEach(item -> {
            int childParentId = item.getParentId();
            if (parentId == childParentId) {
                childList.add(item);
            }
            // 二次循环, 获取子节点的子节点
            childList.forEach(child -> {
                try {
                    child.setChildren(fillChildren(child, originalList));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });
        return childList;
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
