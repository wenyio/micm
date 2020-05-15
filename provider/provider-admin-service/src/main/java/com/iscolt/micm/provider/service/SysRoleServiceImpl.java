package com.iscolt.micm.provider.service;

import com.iscolt.micm.commons.base.repository.BaseRepository;
import com.iscolt.micm.commons.base.services.AbstractCrudService;
import com.iscolt.micm.commons.model.dto.SysRoleTreeDTO;
import com.iscolt.micm.provider.api.SysRoleService;
import com.iscolt.micm.provider.entity.SysRole;
import com.iscolt.micm.provider.entity.SysUser;
import com.iscolt.micm.provider.repository.SysRoleRepository;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色管理
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
public class SysRoleServiceImpl extends AbstractCrudService<SysRole, Integer> implements SysRoleService {

    @Resource
    private SysRoleRepository sysRoleRepository;

    protected SysRoleServiceImpl(BaseRepository<SysRole, Integer> repository) {
        super(repository);
    }

    @Override
    public SysRole create(SysRole sysRole) {
        return sysRoleRepository.save(sysRole);
    }

    @Override
    public List<SysRole> selectRoleByUserId(int uid) {
        return sysRoleRepository.findByUserId(uid);
    }

    /**
     * 组装Tree
     *  1. 获取组织的父节点(0, tenantId)
     *  2. 获取组织父节点的子节点(parentId, tenantId)
     *  3. 获取组织子节点的子节点(parentId, tenantId
     */
    @Override
    public List<SysRoleTreeDTO> selectRoleTreeByTenantId(int tenantId) throws Exception {
        List<SysRole> sysRoles = sysRoleRepository.findByTenantId(tenantId);
        List<SysRoleTreeDTO> sysRoleTreeDTOS = new ArrayList<>();
        // 全部转化成DTO
        sysRoles.forEach(sysRole -> {
            SysRoleTreeDTO roleTreeDTO = new SysRoleTreeDTO();
            BeanUtils.copyProperties(sysRole, roleTreeDTO);
            sysRoleTreeDTOS.add(roleTreeDTO);
        });
        return getTree(sysRoleTreeDTOS);
    }

    /**
     * 把列表转换为树结构
     *
     * @param originalList 原始list数据
     * @return 组装后的集合
     */
    public static List<SysRoleTreeDTO> getTree(List<SysRoleTreeDTO> originalList) throws Exception {

        // 获取根节点，即找出父节点为空的对象
        List<SysRoleTreeDTO> topList = new ArrayList<>();
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
    public static List<SysRoleTreeDTO> fillTree(List<SysRoleTreeDTO> parentList, List<SysRoleTreeDTO> originalList) throws Exception {
        parentList.forEach(parent -> {
            List<SysRoleTreeDTO> children = null;
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
    public static List<SysRoleTreeDTO> fillChildren(SysRoleTreeDTO parent, List<SysRoleTreeDTO> originalList) throws Exception {
        List<SysRoleTreeDTO> childList = new ArrayList<>();
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
