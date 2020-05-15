package com.iscolt.micm.provider.service;

import com.iscolt.micm.commons.base.repository.BaseRepository;
import com.iscolt.micm.commons.base.services.AbstractCrudService;
import com.iscolt.micm.provider.api.ServicePracticeService;
import com.iscolt.micm.provider.entity.ServicePractice;
import com.iscolt.micm.provider.repository.ServicePracticeRepository;
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
 * @date: 2020/5/9
 * @see: com.iscolt.micm.provider.service
 * @version: v1.0.0
 */
@Service(version = "1.0.0")
public class ServicePracticeServiceImpl extends AbstractCrudService<ServicePractice, Long> implements ServicePracticeService {

    @Resource
    private ServicePracticeRepository servicePracticeRepository;

    protected ServicePracticeServiceImpl(BaseRepository<ServicePractice, Long> repository) {
        super(repository);
    }

    @Override
    public List<ServicePractice> search(Integer tenantId, String key) {
        return servicePracticeRepository.findByTenantIdAndTitleLikeOrTenantIdAndDescriptionLike(tenantId, "%"+key+"%", tenantId, "%"+key+"%");
    }

    @Override
    public List<ServicePractice> recommend(Integer tenantId, long organizationId) {
        return servicePracticeRepository.randThree(organizationId, tenantId);
    }

    @Override
    public List<ServicePractice> listByTenantId(int tenantId) {
        return servicePracticeRepository.findByTenantId(tenantId);
    }
}
