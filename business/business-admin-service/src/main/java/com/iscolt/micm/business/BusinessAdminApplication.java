package com.iscolt.micm.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * xx
 *
 * @Author: https://github.com/isColt
 * @Date: 3/31/2020
 * @Description: com.iscolt.micm.business
 * @version: v1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient // 开启服务发现
public class BusinessAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessAdminApplication.class, args);
    }
}
