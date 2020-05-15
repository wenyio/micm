package com.iscolt.micm.cloud;

import com.iscolt.micm.cloud.message.MessageSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/3/2020
 * @see: com.iscolt.micm.cloud
 * @version: v1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(MessageSource.class) // 注册binding
@EnableAsync // 开启异步调用功能
@ComponentScan(basePackages = {"com.iscolt.micm.configuration", "com.iscolt.micm.cloud.**"})
public class CloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudApplication.class, args);
    }

}
