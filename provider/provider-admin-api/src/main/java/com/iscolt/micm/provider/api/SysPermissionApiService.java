package com.iscolt.micm.provider.api;


import com.iscolt.micm.commons.base.services.CrudService;
import com.iscolt.micm.commons.model.dto.SysPermissionApiTreeDTO;
import com.iscolt.micm.commons.provider.view.SysRoleApiView;
import com.iscolt.micm.provider.entity.SysPermissionApi;

import java.util.List;

/**
 * 权限API管理
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/9/2020
 * @see: com.iscolt.micm.provider.api
 * @version: v1.0.0
 */
public interface SysPermissionApiService extends CrudService<SysPermissionApi, Integer> {

    /**
     * 查询有所API
     * @return
     */
    List<SysPermissionApi> selectAll();

    /**
     * 查询所有API及对应的角色
     * @return
     */
    List<SysRoleApiView> selectAllRoleAndApi() throws Exception;

    /**
     * 根据租户id查权限树
     * @param tenantId
     * @return
     * @throws Exception
     */
    List<SysPermissionApiTreeDTO> selectPermissionApiTreeByTenantId(int tenantId) throws Exception;

    /**
     * 根据权限id 获取权限接口
     * @param permissionId
     * @return
     */
    SysPermissionApi getByPermissionId(int permissionId);

    /**
     * 根据权限id 删除
     * @param permissionId
     * @return
     */
    int removeByPermissionId(int permissionId);
}
