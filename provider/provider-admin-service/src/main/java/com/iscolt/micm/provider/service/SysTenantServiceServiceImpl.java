package com.iscolt.micm.provider.service;

import com.iscolt.micm.commons.base.repository.BaseRepository;
import com.iscolt.micm.commons.base.services.AbstractCrudService;
import com.iscolt.micm.provider.api.SysTenantServiceService;
import com.iscolt.micm.provider.entity.SysTenantService;
import com.iscolt.micm.provider.repository.SysTenantServiceRepository;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private SysTenantServiceRepository sysTenantServiceRepository;

    protected SysTenantServiceServiceImpl(BaseRepository<SysTenantService, Integer> repository) {
        super(repository);
    }

    @Override
    public List<SysTenantService> selectByTenantId(Integer tid) {
        return sysTenantServiceRepository.findByTenantId(tid);
    }

    @Override
    public SysTenantService selectByServiceIdAndTenantId(Integer sid, Integer tid) {
        return sysTenantServiceRepository.findByServiceIdAndTenantId(sid, tid);
    }
}
