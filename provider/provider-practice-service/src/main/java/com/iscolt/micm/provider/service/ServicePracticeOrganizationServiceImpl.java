package com.iscolt.micm.provider.service;

import com.iscolt.micm.commons.base.repository.BaseRepository;
import com.iscolt.micm.commons.base.services.AbstractCrudService;
import com.iscolt.micm.commons.model.dto.ServicePracticeCategoryTreeDTO;
import com.iscolt.micm.commons.model.dto.ServicePracticeOrganizationTreeDTO;
import com.iscolt.micm.provider.api.ServicePracticeCategoryService;
import com.iscolt.micm.provider.api.ServicePracticeOrganizationService;
import com.iscolt.micm.provider.entity.ServicePracticeCategory;
import com.iscolt.micm.provider.entity.ServicePracticeOrganization;
import com.iscolt.micm.provider.repository.ServicePracticeOrganizationRepository;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 活动组织（创建活动的组织）
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/10
 * @see: com.iscolt.micm.provider.service
 * @version: v1.0.0
 */
@Service(version = "1.0.0")
public class ServicePracticeOrganizationServiceImpl extends AbstractCrudService<ServicePracticeOrganization, Integer> implements ServicePracticeOrganizationService {

    @Resource
    private ServicePracticeOrganizationRepository servicePracticeOrganizationRepository;

    protected ServicePracticeOrganizationServiceImpl(BaseRepository<ServicePracticeOrganization, Integer> repository) {
        super(repository);
    }

    @Override
    public List<ServicePracticeOrganizationTreeDTO> selectTreeByTenantId(int tenantId) throws Exception {
        List<ServicePracticeOrganization> servicePracticeOrganizations = servicePracticeOrganizationRepository.findByTenantId(tenantId);
        List<ServicePracticeOrganizationTreeDTO> servicePracticeOrganizationTreeDTOS = new ArrayList<>();
        servicePracticeOrganizations.forEach(servicePracticeOrganization -> {
            ServicePracticeOrganizationTreeDTO servicePracticeOrganizationTreeDTO = new ServicePracticeOrganizationTreeDTO();
            BeanUtils.copyProperties(servicePracticeOrganization, servicePracticeOrganizationTreeDTO);
            servicePracticeOrganizationTreeDTOS.add(servicePracticeOrganizationTreeDTO);
        });
        return getTree(servicePracticeOrganizationTreeDTOS);
    }

    @Override
    public List<ServicePracticeOrganization> listByTenantId(int tenantId) {
        return servicePracticeOrganizationRepository.findByTenantId(tenantId);
    }

    /**
     * 把列表转换为树结构
     *
     * @param originalList 原始list数据
     * @return 组装后的集合
     */
    public static List<ServicePracticeOrganizationTreeDTO> getTree(List<ServicePracticeOrganizationTreeDTO> originalList) throws Exception {

        // 获取根节点，即找出父节点为空的对象
        List<ServicePracticeOrganizationTreeDTO> topList = new ArrayList<>();
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
    public static List<ServicePracticeOrganizationTreeDTO> fillTree(List<ServicePracticeOrganizationTreeDTO> parentList, List<ServicePracticeOrganizationTreeDTO> originalList) throws Exception {
        parentList.forEach(parent -> {
            List<ServicePracticeOrganizationTreeDTO> children = null;
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
    public static List<ServicePracticeOrganizationTreeDTO> fillChildren(ServicePracticeOrganizationTreeDTO parent, List<ServicePracticeOrganizationTreeDTO> originalList) throws Exception {
        List<ServicePracticeOrganizationTreeDTO> childList = new ArrayList<>();
        long parentId = parent.getId();
        // 一次循环, 获取子节点
        originalList.forEach(item -> {
            long childParentId = item.getParentId();
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
