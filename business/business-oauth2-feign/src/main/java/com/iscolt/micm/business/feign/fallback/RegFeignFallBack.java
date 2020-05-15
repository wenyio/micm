package com.iscolt.micm.business.feign.fallback;

import com.iscolt.micm.business.dto.RegParam;
import com.iscolt.micm.business.feign.RegFeign;
import com.iscolt.micm.commons.dto.ResponseResult;
import com.iscolt.micm.commons.model.dto.ResponseCode;
import com.iscolt.micm.commons.utils.MapperUtils;
import org.springframework.stereotype.Component;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/6
 * @see: com.iscolt.micm.business.feign.fallback
 * @version: v1.0.0
 */
@Component
public class RegFeignFallBack implements RegFeign{
    public static final String BREAKING_MESSAGE = "请求失败了，请检查您的网络";

    @Override
    public String reg(RegParam regParam) {
        // TODO 判空， 返回空值提示
        try {
            return MapperUtils.obj2json(new ResponseResult<Void>(ResponseCode.BREAKING.code(), BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
