package com.iscolt.micm.business.feign.fallback;

import com.iscolt.micm.business.dto.params.AvatarParam;
import com.iscolt.micm.business.dto.params.PasswordParam;
import com.iscolt.micm.business.dto.params.FaceParam;
import com.iscolt.micm.business.dto.params.ProfileParam;
import com.iscolt.micm.business.feign.ProfileFeign;
import com.iscolt.micm.commons.model.dto.ResponseCode;
import com.iscolt.micm.commons.dto.ResponseResult;
import com.iscolt.micm.commons.utils.MapperUtils;
import org.springframework.stereotype.Component;

/**
 * 个人信息服务熔断器
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/2/2020
 * @see: com.iscolt.micm.business.feign.failback
 * @version: v1.0.0
 */
@Component
public class ProfileFeignFallback implements ProfileFeign {

    public static final String BREAKING_MESSAGE = "请求失败了，请检查您的网络";

    @Override
    public String info(String username) {
        // TODO 根据场景, 熔断后可以返回一些固定得值
//        UmsAdminDTO dto = new UmsAdminDTO();
//        dto.setAvatar("1");

        try {
            return MapperUtils.obj2json(new ResponseResult<Void>(ResponseCode.BREAKING.code(), BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String update(ProfileParam profileParam) {
        try {
            return MapperUtils.obj2json(new ResponseResult<Void>(ResponseCode.BREAKING.code(), BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String modifyPassword(PasswordParam passwordParam) {
        try {
            return MapperUtils.obj2json(new ResponseResult<Void>(ResponseCode.BREAKING.code(), BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String modifyAvatar(AvatarParam avatarParam) {
        try {
            return MapperUtils.obj2json(new ResponseResult<Void>(ResponseCode.BREAKING.code(), BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String modifyFace(FaceParam faceParam) {
        try {
            return MapperUtils.obj2json(new ResponseResult<Void>(ResponseCode.BREAKING.code(), BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
