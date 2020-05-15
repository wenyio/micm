package com.iscolt.micm.cloud.feign.fallback;

import com.iscolt.micm.cloud.feign.MessageFeign;
import org.springframework.stereotype.Component;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/3/2020
 * @see: com.iscolt.micm.cloud.feign.fallback
 * @version: v1.0.0
 */
@Component
public class MessageFeignFallback implements MessageFeign {

    public static final String BREAKING_MESSAGE = "请求失败了，请检查您的网络";

//    @Override
//    public String sendAdminLoginLog(UmsAdminLoginLogDTO dto) {
//        try {
//            return MapperUtils.obj2json(new ResponseResult<Void>(ResponseCode.BREAKING.code(), BREAKING_MESSAGE));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

}
