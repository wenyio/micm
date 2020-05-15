package com.iscolt.micm.business;

import com.iscolt.micm.commons.utils.BaiduAiUtil;
import com.iscolt.micm.commons.utils.IdWorker;
import com.iscolt.micm.configuration.DubboSentinelConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * 认证服务入口
 *
 * @Author: https://github.com/isColt
 * @Date: 3/29/2020
 * @Description: com.iscolt.micm.business
 * @version: v1.0.0
 */
@SpringBootApplication(scanBasePackageClasses = {BusinessOAuth2Application.class}, scanBasePackages = "com.iscolt.micm.cloud.feign")
@EnableDiscoveryClient
@EnableFeignClients
public class BusinessOAuth2Application {

    public static void main(String[] args) {
        SpringApplication.run(BusinessOAuth2Application.class, args);
    }

}
