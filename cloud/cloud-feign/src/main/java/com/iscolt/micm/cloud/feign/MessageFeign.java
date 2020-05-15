package com.iscolt.micm.cloud.feign;

import com.iscolt.micm.cloud.dto.UmsAdminLoginLogDTO;
import com.iscolt.micm.cloud.feign.fallback.MessageFeignFallback;
import com.iscolt.micm.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/3/2020
 * @see: com.iscolt.micm.cloud.feign
 * @version: v1.0.0
 */
@FeignClient(value = "cloud", path = "cloud", configuration = FeignRequestConfiguration.class, fallback = MessageFeignFallback.class)
public interface MessageFeign {

//    @PostMapping(value = "admin/login/log")
//    public String sendAdminLoginLog(@RequestBody UmsAdminLoginLogDTO dto);

}
