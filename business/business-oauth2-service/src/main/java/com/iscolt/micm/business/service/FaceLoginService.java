package com.iscolt.micm.business.service;

import com.baidu.aip.util.Base64Util;
import com.google.common.collect.Maps;
import com.iscolt.micm.business.dto.LoginParam;
import com.iscolt.micm.business.feign.LoginFeign;
import com.iscolt.micm.commons.dto.FaceLoginResult;
import com.iscolt.micm.commons.dto.QRCode;
import com.iscolt.micm.commons.utils.BaiduAiUtil;
import com.iscolt.micm.commons.utils.IdWorker;
import com.iscolt.micm.commons.utils.MapperUtils;
import com.iscolt.micm.commons.utils.OkHttpClientUtil;
import com.iscolt.micm.commons.utils.QRCodeUtil;
import com.iscolt.micm.provider.api.UmsAdminService;
import com.iscolt.micm.provider.entity.UmsAdmin;
import okhttp3.Response;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/4/2020
 * @see: com.iscolt.micm.business.service
 * @version: v1.0.0
 */
@Component
public class FaceLoginService {

    @Value("${qr.url}")
    private String url;

    @Resource
    private IdWorker idWorker;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private BaiduAiUtil baiduAiUtil;

    @Reference(version = "1.0.0")
    private UmsAdminService umsAdminService;

    /**
     * 创建二维码
     *
     * @return
     * @throws Exception
     */
    public QRCode getQRCode() throws Exception {
        // 1. 创建唯一标识
        String code = idWorker.nextId() + "";

        // 2. 生成二维码(url地址)
        String content = url + "?code=" + code;
        String file = QRCodeUtil.crateQRCode(content);

        // 3. 存入当前二维码状态(存入redis)
        FaceLoginResult result = new FaceLoginResult();
        result.setState("-1");
        redisTemplate.boundValueOps(getCacheKey(code)).set(result, 10, TimeUnit.MINUTES); // 状态对象, 失效时间, 单位

        return new QRCode(code, file);
    }

    /**
     * 根据唯一标识，查询用户是否登录成功
     *
     * @param code
     * @return
     */
    public FaceLoginResult checkQRCode(String code) {
        String key = getCacheKey(code);
        return (FaceLoginResult)redisTemplate.opsForValue().get(key);
    }

    /**
     * 扫描二维码之后，使用拍摄照片进行登录
     *  返回登陆成功后的uid
     *  登陆失败: null
     *
     * @param code
     * @param attachment
     * @return
     * @throws Exception
     */
    public String loginByFace(String code, MultipartFile attachment) throws Exception {
        return null;
    }

    /**
     * 构造缓存key
     *
     * @param code
     * @return
     */
    private String getCacheKey(String code) {
        return "qrcode_" + code;
    }

}

