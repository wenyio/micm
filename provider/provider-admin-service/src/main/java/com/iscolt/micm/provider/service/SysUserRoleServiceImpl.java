package com.iscolt.micm.provider.service;

import com.iscolt.micm.commons.base.repository.BaseRepository;
import com.iscolt.micm.commons.base.services.AbstractCrudService;
import com.iscolt.micm.provider.api.SysUserRoleService;
import com.iscolt.micm.provider.entity.SysUser;
import com.iscolt.micm.provider.entity.SysUserRole;
import com.iscolt.micm.provider.repository.SysUserRoleRepository;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * 用户角色管理
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/9/2020
 * @see: com.iscolt.micm.provider.service
 * @version: v1.0.0
 */
@Service(version = "1.0.0")
public class SysUserRoleServiceImpl extends AbstractCrudService<SysUserRole, Integer> implements SysUserRoleService {

    @Resource
    private SysUserRoleRepository sysUserRoleRepository;

    protected SysUserRoleServiceImpl(BaseRepository<SysUserRole, Integer> repository) {
        super(repository);
    }

    @Override
    public int removeByUserIdAndRolId(int userId, int roleId) {
        return sysUserRoleRepository.deleteByUserIdAndRoleId(userId, roleId);
    }

    @Override
    public SysUserRole get(int userId, int roleId) {
        return sysUserRoleRepository.findByUserIdAndRoleId(userId, roleId);
    }

    @Override
    public int removeByUserId(int userId) {
        return sysUserRoleRepository.deleteByUserId(userId);
    }

    @Override
    public int removeByRoleId(int roleId) {
        return sysUserRoleRepository.deleteByRoleId(roleId);
    }
}
