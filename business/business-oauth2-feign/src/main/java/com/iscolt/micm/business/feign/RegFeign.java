package com.iscolt.micm.business.feign;

import com.iscolt.micm.business.dto.RegParam;
import com.iscolt.micm.business.feign.fallback.RegFeignFallBack;
import com.iscolt.micm.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 注册
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/6
 * @see: com.iscolt.micm.business.feign
 * @version: v1.0.0
 */
@FeignClient(value = "business-oauth2", path = "user", configuration = FeignRequestConfiguration.class, fallback = RegFeignFallBack.class)
public interface RegFeign {

    /**
     * 注册/添加用户
     * @param regParam
     * @return
     */
    @PostMapping(value = "/reg")
    String reg(@RequestBody RegParam regParam);
}
