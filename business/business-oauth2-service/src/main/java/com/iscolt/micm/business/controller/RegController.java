package com.iscolt.micm.business.controller;

import com.iscolt.micm.business.dto.RegParam;
import com.iscolt.micm.business.feign.RegFeign;
import com.iscolt.micm.commons.model.dto.ResponseCode;
import com.iscolt.micm.commons.dto.ResponseResult;
import com.iscolt.micm.provider.api.SysUserService;
import com.iscolt.micm.provider.entity.SysUser;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户注册控制器
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/12/2020
 * @see: com.iscolt.micm.business.controller
 * @version: v1.0.0
 */
@RestController
public class RegController {

    @Reference(version = "1.0.0")
    private SysUserService sysUserService;

    /**
     * 用户注册
     *
     * @param regParam {@link RegParam}
     * @return 成功则返回新注册用户信息
     */
    @PostMapping(value = "/user/reg")
    public ResponseResult<SysUser> reg(@RequestBody RegParam regParam) {
        SysUser sysUser = new SysUser();
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            sysUser.setTenantId(0);
        } else {
            SysUser loginUser = sysUserService.get(authentication.getName());
            // 添加用户, 这个用户默认归属添加人的组织
            sysUser.setTenantId(loginUser.getTenantId());
            sysUser.setTenantName(loginUser.getTenantName());
        }

//        BeanUtils.copyProperties(sysUser, regParam);
        sysUser.setNickname(regParam.getNickname());
        sysUser.setUsername(regParam.getUsername());
        sysUser.setPassword(regParam.getPassword());
        sysUser.setPhone(regParam.getPhone());
        sysUser.setEmail(regParam.getEmail());
        sysUser.setAccount(regParam.getAccount());
        sysUser.setStatus(regParam.isStatus());

        String message = validateReg(sysUser);
        // 验证通过 TODO 邮箱, 手机验证, 添加默认角色(可以访问校织网公开服务, 和租户无关)
        if (message == null) {
            sysUser.setAvatar("https://profile.csdnimg.cn/B/4/D/2_weixin_40663067");
            SysUser user = sysUserService.insert(sysUser);
            // 注册成功
            if (null != user) {
                return new ResponseResult<SysUser>(ResponseCode.OK.code(), "新用户注册成功", user);
            }
        }
        return new ResponseResult<SysUser>(ResponseCode.FAIL.code(), message != null ? message : "新用户注册失败");
    }

    /**
     * 验证注册信息
     * @param sysUser {@link SysUser}
     * @return 错误信息
     */
    private String validateReg(SysUser sysUser) {
        SysUser user = sysUserService.get(sysUser.getUsername());
        if (user != null) {
            return "用户名已存在，请重新输入";
        }
        return null;
    }
}
