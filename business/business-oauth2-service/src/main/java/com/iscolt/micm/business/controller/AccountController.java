package com.iscolt.micm.business.controller;

import com.iscolt.micm.business.dto.EmailParam;
import com.iscolt.micm.business.dto.ForgetParam;
import com.iscolt.micm.business.dto.SysUserDTO;
import com.iscolt.micm.business.dto.params.AvatarParam;
import com.iscolt.micm.business.dto.params.PasswordParam;
import com.iscolt.micm.business.dto.params.ProfileParam;
import com.iscolt.micm.cloud.api.MessageService;
import com.iscolt.micm.commons.dto.ResponseResult;
import com.iscolt.micm.commons.model.dto.ResponseCode;
import com.iscolt.micm.provider.api.SysUserService;
import com.iscolt.micm.provider.entity.SysUser;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 账号管理
 *  1. 邮箱绑定
 *  ...
 * <p>
 * Description: 此类需要拥有的角色: visitor
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/11
 * @see: com.iscolt.micm.business.controller
 * @version: v1.0.0
 */
@RestController
@RequestMapping(value = "user/account")
public class AccountController {

    @Reference(version = "1.0.0")
    private SysUserService sysUserService;

    @Reference(version = "1.0.0")
    private MessageService messageService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;


    /**
     * 获取用户基本信息(账号相关)
     * @return
     */
    @GetMapping(value = "info")
    public ResponseResult<SysUserDTO> basicInfo() {
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = sysUserService.get(authentication.getName());
        SysUserDTO dto = new SysUserDTO();
        BeanUtils.copyProperties(sysUser, dto);
        return new ResponseResult<SysUserDTO>(ResponseCode.OK.code(), ResponseCode.OK.message(), dto);
    }

    /**
     * 更新账号信息
     * TODO {@link ProfileParam} 类中的 phone 和 email 需要单独验证修改
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
     * 忘记密码（开放接口)
     *
     * @param forgetParam
     * @return
     */
    @PostMapping(value = "forget/password")
    public ResponseResult<Void> forgetPassword(@RequestBody ForgetParam forgetParam) {
        SysUser sysUser = sysUserService.getByEmail(forgetParam.getEmail());
        if (sysUser == null) {
            return new ResponseResult<Void>(ResponseCode.FAIL.code(), "系统没有此用户");
        }
        String code = (String) redisTemplate.opsForValue().get(forgetParam.getEmail());
        // 验证验证码
        if (code != null && code.equals(forgetParam.getCaptcha())) {
            Boolean delete = redisTemplate.delete(sysUser.getEmail());
            SysUser result = sysUserService.modifyPassword(sysUser.getUsername(), forgetParam.getNewPassword());
            if (result != null) {
                return new ResponseResult<Void>(ResponseCode.OK.code(), "修改密码成功");
            }
        }
        // 验证码不正确
        else {
            return new ResponseResult<Void>(ResponseCode.FAIL.code(), "验证码不正确, 请重试");
        }

        return new ResponseResult<Void>(ResponseCode.FAIL.code(), "修改密码失败");
    }

    /**
     * 发送邮箱验证码
     *    TODO 发送验证码前 判断系统是否存在此邮箱, 判断邮箱格式
     * @param email
     * @return
     */
    @PostMapping(value = "email/code")
    public ResponseResult<Void> sendEmailCode(@RequestBody String email) {
        boolean flag = messageService.sendEmail(email);
        if (flag) {
            return new ResponseResult<Void>(ResponseCode.OK.code(), "验证码已发送");
        }

        else {
            return new ResponseResult<Void>(ResponseCode.FAIL.code(), "验证码发送失败");
        }
    }

    /**
     * 修改邮箱
     *
     * @param emailParam
     * @return
     */
    @PostMapping(value = "modify/email")
    public ResponseResult<Void> modifyEmail(@RequestBody EmailParam emailParam) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(emailParam, sysUser);
        String message = validateEmail(sysUser);

        if (message == null) {
            String code = (String) redisTemplate.opsForValue().get(emailParam.getEmail());
            // 验证验证码
            if (code != null && code.equals(emailParam.getCaptcha())) {
                Boolean delete = redisTemplate.delete(emailParam.getEmail());
                SysUser result = sysUserService.modifyEmail(emailParam.getUsername(), emailParam.getEmail());
                if (result != null) {
                    return new ResponseResult<Void>(ResponseCode.OK.code(), "修改邮箱成功");
                }
            }
            // 验证码不正确
            else {
                return new ResponseResult<Void>(ResponseCode.FAIL.code(), "验证码不正确, 请重试");
            }
        }
        return new ResponseResult<Void>(ResponseCode.FAIL.code(), message != null ? message : "修改邮箱失败");
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
     * 验证注册信息
     * @param sysUser {@link SysUser}
     * @return 错误信息
     */
    private String validateUsername(SysUser sysUser) {
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser user = sysUserService.get(sysUser.getUsername());
        if (user != null) {
            // 用户就是自己的情况
            if (user.getUsername().equals(authentication.getName())) {
                return null;
            }
            return "用户名已存在，请重新输入";
        }
        return null;
    }

    /**
     * 验证注册信息
     * @param sysUser {@link SysUser}
     * @return 错误信息
     */
    private String validateEmail(SysUser sysUser) {
        SysUser user = sysUserService.getByEmail(sysUser.getEmail());
        if (user != null) {
            return "邮箱已存在";
        }
        return null;
    }

    /**
     * 验证注册信息
     * @param sysUser {@link SysUser}
     * @return 错误信息
     */
    private String validatePhone(SysUser sysUser) {
//        // 获取认证信息
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        SysUser user = sysUserService.get(sysUser.getUsername());
//        if (user != null) {
//            // 用户就是自己的情况
//            if (user.getUsername().equals(authentication.getName())) {
//                return null;
//            }
//            return "用户名已存在，请重新输入";
//        }
        return null;
    }
}
