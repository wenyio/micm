/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.iscolt.micm.business.service;

import com.google.common.collect.Lists;
import com.iscolt.micm.provider.api.SysPermissionService;
import com.iscolt.micm.provider.api.SysRoleService;
import com.iscolt.micm.provider.api.SysUserService;
import com.iscolt.micm.provider.api.UmsAdminService;
import com.iscolt.micm.provider.entity.SysPermission;
import com.iscolt.micm.provider.entity.SysRole;
import com.iscolt.micm.provider.entity.SysUser;
import com.iscolt.micm.provider.entity.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义认证授权
 *
 * @Author: https://github.com/isColt
 * @Date: 3/29/2020
 * @Description: com.iscolt.micm.business.service
 * @version: v1.0.0
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Reference(version = "1.0.0")
    private SysUserService sysUserService;

    @Reference(version = "1.0.0")
    private SysRoleService sysRoleService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        // TODO 可以通过正则表达式 判断是邮箱,手机 等方式登录...

        // 查询用户
        SysUser sysUser = sysUserService.get(s);

        // 给每个用户授予 User 权限 TODO 可以通过访问数据库获取权限
        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();

        // 账号存在, 状态正常
        if (sysUser != null && sysUser.isStatus()) {
            // 获取用户授权
            List<SysRole> sysRoles = sysRoleService.selectRoleByUserId(sysUser.getId());

            // 声明用户授权
            sysRoles.forEach(sysRole -> {
                if (sysRole != null && sysRole.getEnname() != null) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysRole.getEnname());
                    grantedAuthorities.add(grantedAuthority);
                }
            });
            // 由框架完成认证工作
            return new User(sysUser.getUsername(), sysUser.getPassword(), grantedAuthorities);
        }

        else {
            return null;
        }
    }
}
