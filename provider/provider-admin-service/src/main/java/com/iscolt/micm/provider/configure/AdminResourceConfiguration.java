package com.iscolt.micm.provider.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * xx
 *
 * @Author: https://github.com/isColt
 * @Date: 3/31/2020
 * @Description: com.iscolt.micm.provider.configure
 * @version: v1.0.0
 */
@Configuration
public class AdminResourceConfiguration {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
