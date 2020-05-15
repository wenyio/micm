package com.iscolt.micm.provider.service;

import com.iscolt.micm.commons.base.repository.BaseRepository;
import com.iscolt.micm.commons.base.services.AbstractCrudService;
import com.iscolt.micm.provider.api.TenantUserInfoService;
import com.iscolt.micm.provider.entity.TenantUserInfo;
import com.iscolt.micm.provider.repository.TenantUserInfoRepository;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;


/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/10
 * @see: com.iscolt.micm.provider.service
 * @version: v1.0.0
 */
@Service(version = "1.0.0")
public class TenantUserInfoServiceImpl extends AbstractCrudService<TenantUserInfo, Integer> implements TenantUserInfoService {

    @Resource
    private TenantUserInfoRepository tenantUserInfoRepository;

    protected TenantUserInfoServiceImpl(BaseRepository<TenantUserInfo, Integer> repository) {
        super(repository);
    }

    @Override
    public TenantUserInfo getByUserId(int userId) {
        return tenantUserInfoRepository.findByUserId(userId);
    }
}
