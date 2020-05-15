package com.iscolt.micm.provider.service;

import com.iscolt.micm.commons.base.repository.BaseRepository;
import com.iscolt.micm.commons.base.services.AbstractCrudService;
import com.iscolt.micm.commons.model.dto.SysPermissionTreeDTO;
import com.iscolt.micm.commons.model.dto.SysRoleTreeDTO;
import com.iscolt.micm.provider.api.SysPermissionService;
import com.iscolt.micm.provider.entity.SysPermission;
import com.iscolt.micm.provider.entity.SysRole;
import com.iscolt.micm.provider.repository.SysPermissionRepository;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 权限管理服务
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/6/2020
 * @see: com.iscolt.micm.provider.service
 * @version: v1.0.0
 */
@Service(version = "1.0.0")
public class SysPermissionServiceImpl extends AbstractCrudService<SysPermission, Integer> implements SysPermissionService {

    @Resource
    private SysPermissionRepository sysPermissionRepository;

    protected SysPermissionServiceImpl(BaseRepository<SysPermission, Integer> repository) {
        super(repository);
    }

    @Override
    public List<SysPermission> selectByUserId(int uid) {
        return sysPermissionRepository.findByUserId(uid);
    }

    @Override
    public List<SysPermission> selectAll() {
        return sysPermissionRepository.findAll();
    }

    @Override
    public List<SysPermissionTreeDTO> selectPermissionTreeByTenantId(int tenantId) throws Exception {
        List<SysPermission> sysPermissions = sysPermissionRepository.findByTenantId(tenantId);
        List<SysPermissionTreeDTO> sysPermissionTreeDTOS = new ArrayList<>();
        // 全部转化成DTO
        sysPermissions.forEach(sysPermission -> {
            SysPermissionTreeDTO SysPermissionTreeDTO = new SysPermissionTreeDTO();
            BeanUtils.copyProperties(sysPermission, SysPermissionTreeDTO);
            sysPermissionTreeDTOS.add(SysPermissionTreeDTO);
        });
        return getTree(sysPermissionTreeDTOS);
    }

    @Override
    public List<List<Integer>> selectPermissionArrayByRoleId(int roleId) {
        List<List<Integer>> allArray = new ArrayList<>();
        // 获取角色下的所有权限
        List<SysPermission> sysPermissions = null;
        sysPermissions = sysPermissionRepository.findByRoleId(roleId);

        if (sysPermissions.get(0) == null) { // 防止空指针
            return null;
        }

        sysPermissions.forEach(sysPermission -> {
            // 权限数组 => [1-id, 2-id, 3-id, ...],
            List<Integer> array = new ArrayList<>();
            List<Integer> stack = new ArrayList<>(); // 一个栈
            // 如果父权限id为0 => [1-id]
            if (sysPermission.getParentId() == 0) {
                array.add(sysPermission.getId());
                allArray.add(array);
            }

            else {
                stack.add(sysPermission.getId()); // 最低级入栈, 先进后出, 最低级最后出
                SysPermission parent = new SysPermission();
                parent = sysPermissionRepository.findById(sysPermission.getParentId()).get();

                while (parent.getParentId() != 0) {
                    stack.add(parent.getId());
                    parent = sysPermissionRepository.findById(parent.getParentId()).get(); // 重值对象
                }

                stack.add(parent.getId());

                Collections.reverse(stack); // 将栈反转
                array = stack;
                allArray.add(array);
            }
        });

        return allArray;
    }

    /**
     * 把列表转换为树结构
     *
     * @param originalList 原始list数据
     * @return 组装后的集合
     */
    public static List<SysPermissionTreeDTO> getTree(List<SysPermissionTreeDTO> originalList) throws Exception {

        // 获取根节点，即找出父节点为空的对象
        List<SysPermissionTreeDTO> topList = new ArrayList<>();
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
    public static List<SysPermissionTreeDTO> fillTree(List<SysPermissionTreeDTO> parentList, List<SysPermissionTreeDTO> originalList) throws Exception {
        parentList.forEach(parent -> {
            List<SysPermissionTreeDTO> children = null;
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
    public static List<SysPermissionTreeDTO> fillChildren(SysPermissionTreeDTO parent, List<SysPermissionTreeDTO> originalList) throws Exception {
        List<SysPermissionTreeDTO> childList = new ArrayList<>();
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
}
