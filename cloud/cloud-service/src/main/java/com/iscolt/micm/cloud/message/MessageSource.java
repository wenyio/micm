package com.iscolt.micm.cloud.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/3/2020
 * @see: com.iscolt.micm.cloud.message
 * @version: v1.0.0
 */
public interface MessageSource {

    @Output("sys-log-topic")
    MessageChannel adminLoginLog();

    @Output("sys-email-topic")
    MessageChannel sendEmail();
}
