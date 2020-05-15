package com.iscolt.micm.cloud.producer;

import com.iscolt.micm.cloud.api.MessageService;
import com.iscolt.micm.cloud.dto.EmailDTO;
import com.iscolt.micm.cloud.dto.SysLogDTO;
import com.iscolt.micm.cloud.message.MessageSource;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/3/2020
 * @see: com.iscolt.micm.cloud.producer
 * @version: v1.0.0
 */
@Component
@Service(version = "1.0.0")
public class MessageProducer implements MessageService {

    @Resource
    private MessageSource source;

    /**
     * 管理登录日志
     *
     * @param dto {@link SysLogDTO}
     * @return {@code boolean}
     */
    public boolean sendSysLog(SysLogDTO dto) {
        return source.adminLoginLog().send(MessageBuilder.withPayload(dto).build());
    }

    /**
     * 发送邮件
     * @return
     */
    public boolean sendEmail(String email) {
        return source.sendEmail().send(MessageBuilder.withPayload(email).build());
    }
}
