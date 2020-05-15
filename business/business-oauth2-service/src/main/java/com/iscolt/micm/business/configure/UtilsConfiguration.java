package com.iscolt.micm.business.configure;

import com.iscolt.micm.commons.utils.BaiduAiUtil;
import com.iscolt.micm.commons.utils.IdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 工具类注入
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/4/2020
 * @see: com.iscolt.micm.business.configure
 * @version: v1.0.0
 */
@Configuration
public class UtilsConfiguration {

    /**
     * 初始化 IdWorker
     * @return
     */
    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }

    /**
     * 初始化 BaiduAiUtil
     * @return
     */
    @Bean
    public BaiduAiUtil baiduAiUtil() {
        return new BaiduAiUtil();
    }

}
