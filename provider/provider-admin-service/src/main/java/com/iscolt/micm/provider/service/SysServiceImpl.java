package com.iscolt.micm.provider.service;

import com.iscolt.micm.commons.base.repository.BaseRepository;
import com.iscolt.micm.commons.base.services.AbstractCrudService;
import com.iscolt.micm.provider.api.SysServiceService;
import com.iscolt.micm.provider.entity.SysService;
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
public class SysServiceImpl extends AbstractCrudService<SysService, Integer> implements SysServiceService {
    protected SysServiceImpl(BaseRepository<SysService, Integer> repository) {
        super(repository);
    }
}
