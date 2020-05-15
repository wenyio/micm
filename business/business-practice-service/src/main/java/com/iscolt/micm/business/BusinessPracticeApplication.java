package com.iscolt.micm.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/10
 * @see: com.iscolt.micm.business
 * @version: v1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient // 开启服务发现
@ComponentScan(basePackages = {"com.iscolt.micm.configuration", "com.iscolt.micm.business.**"})
public class BusinessPracticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessPracticeApplication.class, args);
    }
}
