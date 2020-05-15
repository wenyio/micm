package com.iscolt.micm.provider.service;

import com.iscolt.micm.provider.api.SysLogService;
import com.iscolt.micm.provider.entity.SysLog;
import com.iscolt.micm.provider.repository.SysLogRepository;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/14/2020
 * @see: com.iscolt.micm.provider.service
 * @version: v1.0.0
 */
@Service(version = "1.0.0")
public class SysLogServiceImpl implements SysLogService {

    @Resource
    private SysLogRepository sysLogRepository;

    @Override
    public SysLog insert(SysLog sysLog) {
        return sysLogRepository.save(sysLog);
    }
}
