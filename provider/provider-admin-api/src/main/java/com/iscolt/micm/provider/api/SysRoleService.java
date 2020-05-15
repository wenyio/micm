package com.iscolt.micm.provider.api;

import com.iscolt.micm.commons.base.services.CrudService;
import com.iscolt.micm.commons.model.dto.SysRoleTreeDTO;
import com.iscolt.micm.provider.entity.SysPermission;
import com.iscolt.micm.provider.entity.SysRole;
import com.iscolt.micm.provider.entity.SysUser;

import java.util.List;

/**
 * 角色管理
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/9/2020
 * @see: com.iscolt.micm.provider.api
 * @version: v1.0.0
 */
public interface SysRoleService extends CrudService<SysRole, Integer> {

    /**
     * 查询根据用户id查询用户角色
     * @param uid
     * @return
     */
    List<SysRole> selectRoleByUserId(int uid);

    /**
     * 获取对应组织下的角色树
     * @return
     */
    List<SysRoleTreeDTO> selectRoleTreeByTenantId(int tenantId) throws Exception;

}
