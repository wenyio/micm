package com.iscolt.micm.message.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/10
 * @see: com.iscolt.micm.message.sink
 * @version: v1.0.0
 */
public interface SysEmailSink {

    @Input("sys-email-topic")
    SubscribableChannel sysEmail();
}
