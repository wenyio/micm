package com.iscolt.micm.provider.api;

import org.springframework.data.domain.Page;
import com.iscolt.micm.commons.base.services.CrudService;
import com.iscolt.micm.provider.entity.SysUser;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

/**
 * 用户管理
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/6/2020
 * @see: com.iscolt.micm.provider.api
 * @version: v1.0.0
 */
public interface SysUserService extends CrudService<SysUser, Integer> {

    /**
     * 新增用户
     * @param sysUser
     * @return
     */
    SysUser insert(SysUser sysUser);

    /**
     * 根据用户名获取
     * @param username
     * @return
     */
    SysUser get(String username);

    /**
     * 根据用户id获取
     * @param id
     * @return
     */
    SysUser getById(Integer id);

    /**
     * 根据邮箱获取
     * @param email
     * @return
     */
    SysUser getByEmail(String email);

    /**
     * 根据手机号获取
     * @param phone
     * @return
     */
    SysUser getByPhone(String phone);

    /**
     * 更新用户
     * @param sysUser
     * @return
     */
    SysUser update(SysUser sysUser);

    /**
     * 修改密码
     * @param username
     * @param passwrod
     * @return
     */
    SysUser modifyPassword(String username, String passwrod);

    /**
     * 修改头像
     * @param username
     * @param path 头像路径
     * @return
     */
    SysUser modifyAvatar(String username, String path);

    /**
     * 修改邮箱
     * @param username
     * @param email
     * @return
     */
    SysUser modifyEmail(String username, String email);

    /**
     * 修改手机号
     * @param username
     * @param phone
     * @return
     */
    SysUser modifyPhone(String username, String phone);

    /**
     * 修改照片
     * @param username
     * @param path
     * @return
     */
    SysUser modifyFace(String username, String path);

    /**
     * 根据租户id查询所有用户
     *
     * @param pageable pageable
     * @return Page
     */
    @NonNull
    Page<SysUser> listAll(Integer tenantId, @NonNull Pageable pageable);

    /**
     * 修改用户状态
     *
     * @param username
     * @return
     */
    SysUser changeStatus(String username);

    /**
     * 假删除
     *
     * @param username
     * @return
     */
    SysUser changeDeleted(String username);
}
