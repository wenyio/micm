package com.iscolt.micm.business.feign;

import com.iscolt.micm.business.dto.LoginParam;
import com.iscolt.micm.commons.dto.ResponseResult;
import com.iscolt.micm.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 登录
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/4/2020
 * @see: com.iscolt.micm.business.feign
 * @version: v1.0.0
 */
@FeignClient(value = "business-oauth2", path = "user", configuration = FeignRequestConfiguration.class)
public interface LoginFeign {

    /**
     * 用户登录
     *
     * @param loginParam
     * @param request
     * @return
     */
    @PostMapping(value = "/login")
    String login(@RequestBody LoginParam loginParam, HttpServletRequest request);

}
