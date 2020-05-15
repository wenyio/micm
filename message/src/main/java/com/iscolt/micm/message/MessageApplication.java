package com.iscolt.micm.message;

import com.iscolt.micm.message.sink.SysEmailSink;
import com.iscolt.micm.message.sink.SysLogSink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/14/2020
 * @see: com.iscolt.micm.message
 * @version: v1.0.0
 */
@SpringBootApplication
@EnableBinding({SysLogSink.class, SysEmailSink.class})
@EnableAsync // 开启异步调用功能
public class MessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageApplication.class, args);
    }
}
