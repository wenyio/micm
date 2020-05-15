package com.iscolt.micm.configuration;

import com.iscolt.micm.interceptor.FeignRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign 全局配置
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 3/31/2020
 * @see: com.iscolt.micm.configuration
 * @version: v1.0.0
 */
@Configuration
public class FeignRequestConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignRequestInterceptor();
    }
}
