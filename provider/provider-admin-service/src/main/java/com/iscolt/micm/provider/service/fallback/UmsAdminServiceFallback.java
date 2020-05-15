package com.iscolt.micm.provider.service.fallback;

import com.iscolt.micm.provider.entity.UmsAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用户服务提供者熔断器(Dubbo Sentinel)
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/2/2020
 * @see: com.iscolt.micm.provider.service.fallback
 * @version: v1.0.0
 */
public class UmsAdminServiceFallback {

    private static final Logger logger = LoggerFactory.getLogger(UmsAdminServiceFallback.class);

    public static UmsAdmin getByUsernameFallback(String username, Throwable ex){
        logger.warn("invoke getByUsernameFallback: " + ex.getClass().getTypeName());
        return null;
    }

}
