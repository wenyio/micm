package com.iscolt.micm.provider.service;

import com.iscolt.micm.commons.base.repository.BaseRepository;
import com.iscolt.micm.commons.base.services.AbstractCrudService;
import com.iscolt.micm.provider.api.SysRolePermissionService;
import com.iscolt.micm.provider.entity.SysRolePermission;
import com.iscolt.micm.provider.entity.SysUser;
import com.iscolt.micm.provider.repository.SysRolePermissionRepository;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/3
 * @see: com.iscolt.micm.provider.service
 * @version: v1.0.0
 */
@Service(version = "1.0.0")
public class SysRolePermissionServiceImpl extends AbstractCrudService<SysRolePermission, Integer> implements SysRolePermissionService {

    @Resource
    private SysRolePermissionRepository sysRolePermissionRepository;

    protected SysRolePermissionServiceImpl(BaseRepository<SysRolePermission, Integer> repository) {
        super(repository);
    }

    @Override
    public int removeByRoleId(int roleId) {
        return sysRolePermissionRepository.deleteByRoleId(roleId);
    }

    @Override
    public int removeByPermissionId(int permissionId) {
        return sysRolePermissionRepository.deleteByPermissionId(permissionId);
    }

    @Override
    public SysRolePermission get(int roleId, int permissionId) {
        return sysRolePermissionRepository.findByRoleIdAndPermissionId(roleId, permissionId);
    }
}
