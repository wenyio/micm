package com.iscolt.micm.business.controller.fallback;

import com.iscolt.micm.business.dto.UmsAdminDTO;
import com.iscolt.micm.business.feign.fallback.ProfileFeignFallback;
import com.iscolt.micm.commons.model.dto.ResponseCode;
import com.iscolt.micm.commons.dto.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/2/2020
 * @see: com.iscolt.micm.business.controller.fallback
 * @version: v1.0.0
 */
public class ProfileControllerFallback {

    private static final Logger logger = LoggerFactory.getLogger(ProfileControllerFallback.class);

    public static ResponseResult<UmsAdminDTO> infoFallback(String username, Throwable ex) {
        logger.warn("invoke infoFallback: " + ex.getClass().getTypeName());
        return new ResponseResult<UmsAdminDTO>(ResponseCode.BREAKING.code(), ProfileFeignFallback.BREAKING_MESSAGE);
    }

}
