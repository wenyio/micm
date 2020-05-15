package com.iscolt.micm.provider.api;

import com.iscolt.micm.commons.base.services.CrudService;
import com.iscolt.micm.provider.entity.SysUserRole;

/**
 * 用户角色管理
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/9/2020
 * @see: com.iscolt.micm.provider.api
 * @version: v1.0.0
 */
public interface SysUserRoleService extends CrudService<SysUserRole, Integer> {

    /**
     * 根据用户删除
     * @param userId
     * @return
     */
    int removeByUserId(int userId);

    /**
     * 根据角色删除
     * @param roleId
     * @return
     */
    int removeByRoleId(int roleId);

    /**
     * 根据用户和角色删除
     * @param userId
     * @param roleId
     * @return
     */
    int removeByUserIdAndRolId(int userId, int roleId);

    /**
     * 根据用户id和角色id查询
     * @param userId
     * @param roleId
     * @return
     */
    SysUserRole get(int userId, int roleId);
}
