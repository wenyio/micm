package com.iscolt.micm.business.controller;

import com.iscolt.micm.business.dto.RegParam;
import com.iscolt.micm.business.feign.RegFeign;
import com.iscolt.micm.commons.dto.ResponseResult;
import com.iscolt.micm.commons.model.dto.ResponseCode;
import com.iscolt.micm.commons.utils.MapperUtils;
import com.iscolt.micm.provider.api.SysRoleService;
import com.iscolt.micm.provider.api.SysUserService;
import com.iscolt.micm.provider.entity.SysRole;
import com.iscolt.micm.provider.entity.SysUser;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;

/**
 * 用户管理
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/20/2020
 * @see: com.iscolt.micm.business.controller
 * @version: v1.0.0
 */
@RestController
@RequestMapping(value = "admin/users")
public class UserController {

    @Resource
    private RegFeign regFeign;

    @Reference(version = "1.0.0")
    private SysUserService sysUserService;

    @Reference(version = "1.0.0")
    private SysRoleService sysRoleService;

    /**
     * 判断权限获取所能管理的用户
     * @return
     */
    @GetMapping(value = "")
    public ResponseResult<Page<SysUser>> list(@PageableDefault(sort = {"created"}, direction = ASC) Pageable pageable) {
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = sysUserService.get(authentication.getName());
        Page<SysUser> sysUsers = sysUserService.listAll(sysUser.getTenantId(), pageable);
        return new ResponseResult<Page<SysUser>>(ResponseCode.OK.code(), "查询成功", sysUsers);
    }

    /**
     * 用户添加(TODO 内部调用注册接口， 判空)
     *
     * @param regParam {@link RegParam}
     * @return
     */
    @PostMapping(value = "/add")
    public ResponseResult<Void> reg(@RequestBody RegParam regParam) throws Exception {

        SysUser sysUser = new SysUser();
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser loginUser = sysUserService.get(authentication.getName());
        sysUser.setTenantId(loginUser.getTenantId());
        sysUser.setTenantName(loginUser.getTenantName());
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
                return new ResponseResult<Void>(ResponseCode.OK.code(), "新用户添加成功");
            }
        }
        return new ResponseResult<Void>(ResponseCode.FAIL.code(), message != null ? message : "新用户添加失败");
    }

    /**
     * 查询用户的角色
     * @return
     */
    @GetMapping(value = "{id}/getRole")
    public ResponseResult<List<Integer>> getRoleByUserId(@PathVariable(value = "id") int id) {
        List<SysRole> sysRoles = sysRoleService.selectRoleByUserId(id);
        List<Integer> roles = new ArrayList<>();
        sysRoles.forEach(item -> {
            roles.add(item.getId());
        });
        return new ResponseResult<>(ResponseCode.OK.code(), "查询成功", roles);
    }

    /**
     * 改变用户状态
     * @return
     */
    @GetMapping(value = "status/{username}")
    public ResponseResult<Page<SysUser>> changeStatus(@PathVariable(value = "username") String username) {
        SysUser sysUser = sysUserService.changeStatus(username);

        if (sysUser != null) {
            return new ResponseResult<Page<SysUser>>(ResponseCode.OK.code(), ResponseCode.OK.message());
        }

        return new ResponseResult<Page<SysUser>>(ResponseCode.FAIL.code(), ResponseCode.FAIL.message());
    }

    /**
     * 改变用户状态
     * @return
     */
    @GetMapping(value = "delete/{username}")
    public ResponseResult<Page<SysUser>> delete(@PathVariable(value = "username") String username) {
        SysUser sysUser = sysUserService.changeDeleted(username);

        if (sysUser != null) {
            return new ResponseResult<Page<SysUser>>(ResponseCode.OK.code(), ResponseCode.OK.message());
        }

        return new ResponseResult<Page<SysUser>>(ResponseCode.FAIL.code(), ResponseCode.FAIL.message());
    }

    /**
     * 验证注册信息 TODO 可以做成工具类, 多处需要
     * @param sysUser {@link SysUser}
     * @return 错误信息
     */
    private String validateReg(SysUser sysUser) {
        SysUser user = sysUserService.get(sysUser.getUsername());
        if (user != null) {
            return "用户名已存在，请重新输入";
        }

        if (sysUser.getUsername() == null || sysUser.getUsername() == "") {
            return "用户名为空";
        }

        if (sysUser.getPassword() == null || sysUser.getPassword() == "") {
            return "用户密码为空";
        }

        if (sysUser.getEmail() == null || sysUser.getEmail() == "") {
            return "用户邮箱为空";
        }
        return null;
    }
}
