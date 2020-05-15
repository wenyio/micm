package com.iscolt.micm.provider.api;

import com.iscolt.micm.provider.entity.SysLog;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/14/2020
 * @see: com.iscolt.micm.provider.api
 * @version: v1.0.0
 */
public interface SysLogService {

    /**
     * 新增日志
     *
     * @param sysLog
     * @return
     */
    SysLog insert(SysLog sysLog);
}
