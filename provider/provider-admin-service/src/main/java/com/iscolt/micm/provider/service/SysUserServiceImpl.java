package com.iscolt.micm.provider.service;

import com.iscolt.micm.commons.base.repository.BaseRepository;
import com.iscolt.micm.commons.base.services.AbstractCrudService;
import com.iscolt.micm.provider.api.SysUserService;
import com.iscolt.micm.provider.entity.SysUser;
import com.iscolt.micm.provider.repository.SysUserRepository;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 用户管理服务
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/6/2020
 * @see: com.iscolt.micm.provider.service
 * @version: v1.0.0
 */
@Service(version = "1.0.0")
public class SysUserServiceImpl extends AbstractCrudService<SysUser, Integer> implements SysUserService {

    @Resource
    private SysUserRepository sysUserRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    protected SysUserServiceImpl(BaseRepository<SysUser, Integer> repository) {
        super(repository);
    }

    @Override
    public SysUser insert(SysUser sysUser) {
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        return sysUserRepository.save(sysUser);
    }

    @Override
    public SysUser get(String username) {
        return sysUserRepository.findByUsername(username);
    }

    @Override
    public SysUser getById(Integer id) {
        return sysUserRepository.findById(id).get();
    }

    @Override
    public SysUser getByEmail(String email) {
        return sysUserRepository.findByEmail(email);
    }

    @Override
    public SysUser getByPhone(String phone) {
        return sysUserRepository.findByPhone(phone);
    }

    @Override
    public SysUser update(SysUser sysUser) {
        SysUser oldSysUser = getById(sysUser.getId());
        // TODO 更新逻辑需要修改
        oldSysUser.setUpdated(new Timestamp(new Date().getTime()));
        oldSysUser.setPhone(sysUser.getPhone());
        oldSysUser.setEmail(sysUser.getEmail());
        oldSysUser.setNickname(sysUser.getNickname());
        oldSysUser.setUsername(sysUser.getUsername());

        return sysUserRepository.save(oldSysUser);
    }

    @Override
    public SysUser modifyPassword(String username, String passwrod) {
        SysUser sysUser = get(username);
        sysUser.setPassword(passwordEncoder.encode(passwrod));
        return sysUserRepository.save(sysUser);
    }

    @Override
    public SysUser modifyAvatar(String username, String path) {
        SysUser sysUser = get(username);
        sysUser.setAvatar(path);
        return sysUserRepository.save(sysUser);
    }

    @Override
    public SysUser modifyEmail(String username, String email) {
        SysUser sysUser = get(username);
        sysUser.setEmail(email);
        return sysUserRepository.save(sysUser);
    }

    @Override
    public SysUser modifyPhone(String username, String phone) {
        SysUser sysUser = get(username);
        sysUser.setPhone(phone);
        return sysUserRepository.save(sysUser);
    }

    @Override
    public SysUser modifyFace(String username, String path) {
        return null;
    }

    @Override
    public Page<SysUser> listAll(Integer tenantId, Pageable pageable) {
        return sysUserRepository.findAllByTenantIdAndDeleted(tenantId,true, pageable);
    }

    @Override
    public SysUser changeStatus(String username) {
        SysUser sysUser = get(username);
        if (sysUser != null) {
            sysUser.setStatus(!sysUser.isStatus());
            sysUserRepository.save(sysUser);
            return sysUser;
        }
        return null;
    }

    @Override
    public SysUser changeDeleted(String username) {
        SysUser sysUser = get(username);
        if (sysUser != null) {
            sysUser.setDeleted(false);
            sysUserRepository.save(sysUser);
            return sysUser;
        }
        return null;
    }
}
