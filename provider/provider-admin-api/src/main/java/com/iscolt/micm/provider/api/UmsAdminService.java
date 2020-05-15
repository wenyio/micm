package com.iscolt.micm.provider.api;

import com.iscolt.micm.provider.entity.UmsAdmin;

/**
 * 用户管理服务
 *
 * @Author: https://github.com/isColt
 * @Date: 3/30/2020
 * @Description: com.iscolt.micm.provider.api
 * @version: v1.0.0
 */
public interface UmsAdminService {

    /**
     * 新增用户
     * @param umsAdmin
     * @return
     */
    UmsAdmin insert(UmsAdmin umsAdmin);

    /**
     * 根据用户名获取
     * @param username
     * @return
     */
    UmsAdmin get(String username);

    /**
     * 根据用户id获取
     * @param id
     * @return
     */
    UmsAdmin getById(Integer id);

    /**
     * 更新用户
     * @param umsAdmin
     * @return
     */
    UmsAdmin update(UmsAdmin umsAdmin);

    /**
     * 修改密码
     * @param username
     * @param passwrod
     * @return
     */
    UmsAdmin modifyPassword(String username, String passwrod);

    /**
     * 修改头像
     * @param username
     * @param path 头像路径
     * @return
     */
    UmsAdmin modifyAvatar(String username, String path);

    /**
     * 修改照片
     * @param username
     * @param path
     * @return
     */
    UmsAdmin modifyFace(String username, String path);
}
