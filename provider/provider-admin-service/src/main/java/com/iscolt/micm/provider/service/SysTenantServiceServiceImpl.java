package com.iscolt.micm.provider.service;

import com.iscolt.micm.commons.base.repository.BaseRepository;
import com.iscolt.micm.commons.base.services.AbstractCrudService;
import com.iscolt.micm.provider.api.SysTenantServiceService;
import com.iscolt.micm.provider.entity.SysTenantService;
import org.apache.dubbo.config.annotation.Service;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/16
 * @see: com.iscolt.micm.provider.service
 * @version: v1.0.0
 */
@Service(version = "1.0.0")
public class SysTenantServiceServiceImpl extends AbstractCrudService<SysTenantService, Integer> implements SysTenantServiceService {
    protected SysTenantServiceServiceImpl(BaseRepository<SysTenantService, Integer> repository) {
        super(repository);
    }
}
