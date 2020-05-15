package com.iscolt.micm.business.feign;

import com.iscolt.micm.business.dto.params.AvatarParam;
import com.iscolt.micm.business.dto.params.PasswordParam;
import com.iscolt.micm.business.dto.params.FaceParam;
import com.iscolt.micm.business.dto.params.ProfileParam;
import com.iscolt.micm.business.feign.fallback.ProfileFeignFallback;
import com.iscolt.micm.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 个人信息管理
 *
 * @Author: https://github.com/isColt
 * @Date: 3/31/2020
 * @Description: com.iscolt.micm.business.feign
 * @version: v1.0.0
 */
@FeignClient(value = "business-admin", path = "admin/profile", configuration = FeignRequestConfiguration.class, fallback = ProfileFeignFallback.class)
public interface ProfileFeign {

    /**
     * 获取个人信息
     * Feign 帮忙直接请求, 返回JSONString
     * @param username
     * @return
     */
    @GetMapping(value = "info/{username}")
    String info(@PathVariable String username);

    /**
     * 更新个人信息
     * @param profileParam
     * @return
     */
    @PostMapping(value = "update")
    String update(@RequestBody ProfileParam profileParam);

    /**
     * 修改密码
     * @param passwordParam
     * @return
     */
    @PostMapping(value = "modify/password")
    String modifyPassword(@RequestBody PasswordParam passwordParam);

    /**
     * 修改头像
     * @param avatarParam
     * @return
     */
    @PostMapping(value = "modify/avatar")
    String modifyAvatar(@RequestBody AvatarParam avatarParam);

    /**
     * 修改照片
     * @param faceParam
     * @return
     */
    @PostMapping(value = "modify/face")
    String modifyFace(@RequestBody FaceParam faceParam);
}
