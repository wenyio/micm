package com.iscolt.micm.message.consumer;

import com.iscolt.micm.commons.utils.MapperUtils;
import com.iscolt.micm.provider.api.SysLogService;
import com.iscolt.micm.provider.entity.SysLog;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 * 日志消费者
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/14/2020
 * @see: com.iscolt.micm.message.consumer
 * @version: v1.0.0
 */
@Service
public class SysLogReceive {

    @Reference(version = "1.0.0")
    private SysLogService sysLogService;

    @StreamListener("sys-log-topic")
    public void receiveAdminLoginLog(String sysLogJson) throws Exception {
        SysLog sysLog = MapperUtils.json2pojo(sysLogJson, SysLog.class);
        sysLogService.insert(sysLog);
    }
}
