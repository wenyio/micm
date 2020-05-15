package com.iscolt.micm.provider;

import com.iscolt.micm.configuration.DubboSentinelConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/9
 * @see: com.iscolt.micm.provider
 * @version: v1.0.0
 */
@SpringBootApplication(scanBasePackageClasses = {ProviderPracticeApplication.class, DubboSentinelConfiguration.class})
public class ProviderPracticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderPracticeApplication.class, args);
    }
}