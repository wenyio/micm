package com.iscolt.micm.business.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.iscolt.micm.business.controller.fallback.ProfileControllerFallback;
import com.iscolt.micm.business.dto.SysUserDTO;
import com.iscolt.micm.business.dto.params.AvatarParam;
import com.iscolt.micm.business.dto.params.PasswordParam;
import com.iscolt.micm.business.dto.params.FaceParam;
import com.iscolt.micm.business.dto.params.ProfileParam;
import com.iscolt.micm.commons.model.dto.ResponseCode;
import com.iscolt.micm.commons.dto.ResponseResult;
import com.iscolt.micm.provider.api.SysUserService;
import com.iscolt.micm.provider.api.UmsAdminService;
import com.iscolt.micm.provider.entity.SysUser;
import com.iscolt.micm.provider.entity.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 个人信息管理
 *
 * @Author: https://github.com/isColt
 * @Date: 3/31/2020
 * @Description: com.iscolt.micm.business.controller
 * @version: v1.0.0
 */
@RestController
@RequestMapping(value = "admin/profile")
public class ProfileController {

    @Reference(version = "1.0.0")
    private UmsAdminService umsAdminService;

    @Reference(version = "1.0.0")
    private SysUserService sysUserService;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 获取个人信息
     *
     * @param username
     * @return
     */
    @GetMapping(value = "info/{username}")
    @SentinelResource(value = "info", fallback = "infoFallback", fallbackClass = ProfileControllerFallback.class)
    public ResponseResult<SysUserDTO> info(@PathVariable String username) {
        SysUser sysUser = sysUserService.get(username);
        SysUserDTO dto  = new SysUserDTO();
        BeanUtils.copyProperties(sysUser, dto);
        return new ResponseResult<SysUserDTO>(ResponseCode.OK.code(), "查询用户信息", dto);
    }

    /**
     * 更新个人信息
     *
     * @param profileParam
     * @return
     */
    @PostMapping(value = "update")
    public ResponseResult<Void> update(@RequestBody ProfileParam profileParam) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(profileParam, sysUser);
        String message = validateUsername(sysUser);

        if (message == null) {
            SysUser result = sysUserService.update(sysUser);

            // 成功
            if (result != null) {
                return new ResponseResult<Void>(ResponseCode.OK.code(), "更新个人信息成功");
            }

            // 失败
            else {
                return new ResponseResult<Void>(ResponseCode.FAIL.code(), "更新个人信息失败");
            }
        }

        return new ResponseResult<Void>(ResponseCode.FAIL.code(), message != null ? message : "更新个人信息失败");
    }

    /**
     * 修改密码
     *
     * @param passwordParam
     * @return
     */
    @PostMapping(value = "modify/password")
    public ResponseResult<Void> modifyPassword(@RequestBody PasswordParam passwordParam) {
        SysUser sysUser = sysUserService.get(passwordParam.getUsername());

        // 旧密码正确
        if (passwordEncoder.matches(passwordParam.getOldPassword(), sysUser.getPassword())) {
            SysUser result = sysUserService.modifyPassword(sysUser.getUsername(), passwordParam.getNewPassword());
            if (result != null) {
                return new ResponseResult<Void>(ResponseCode.OK.code(), "修改密码成功");
            }
        }

        // 旧密码不正确
        else {
            return new ResponseResult<Void>(ResponseCode.FAIL.code(), "旧密码不匹配, 请重试");
        }

        return new ResponseResult<Void>(ResponseCode.FAIL.code(), "修改密码失败");
    }

    /**
     * 修改头像
     *
     * @param avatarParam
     * @return
     */
    @PostMapping(value = "modify/avatar")
    public ResponseResult<Void> modifyAvatar(@RequestBody AvatarParam avatarParam) {
        SysUser result = sysUserService.modifyAvatar(avatarParam.getUsername(), avatarParam.getPath());

        // 成功
        if (result != null) {
            return new ResponseResult<Void>(ResponseCode.OK.code(), "更新头像成功");
        }

        // 失败
        else {
            return new ResponseResult<Void>(ResponseCode.FAIL.code(), "更新头像失败");
        }
    }

    /**
     * 修改照片
     *
     * @param faceParam
     * @return
     */
    @PostMapping(value = "modify/face")
    public ResponseResult<Void> modifyFave(@RequestBody FaceParam faceParam) {

        UmsAdmin result = umsAdminService.modifyFace(faceParam.getUsername(), faceParam.getPath());

        // 成功
        if (result != null) {
            return new ResponseResult<Void>(ResponseCode.OK.code(), "更新头像成功");
        }

        // 失败
        else {
            return new ResponseResult<Void>(ResponseCode.FAIL.code(), "更新头像失败");
        }
    }

    /**
     * 验证注册信息
     * @param sysUser {@link SysUser}
     * @return 错误信息
     */
    private String validateUsername(SysUser sysUser) {
        SysUser user = sysUserService.get(sysUser.getUsername());
        if (user != null) {
            return "用户名已存在，请重新输入";
        }
        return null;
    }
}
