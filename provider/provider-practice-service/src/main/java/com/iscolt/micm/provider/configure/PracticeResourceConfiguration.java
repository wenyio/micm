package com.iscolt.micm.provider.configure;

import com.iscolt.micm.commons.utils.IdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/9
 * @see: com.iscolt.micm.provider.configure
 * @version: v1.0.0
 */
@Configuration
public class PracticeResourceConfiguration {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 初始化 IdWorker
     * @return
     */
    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }

}
