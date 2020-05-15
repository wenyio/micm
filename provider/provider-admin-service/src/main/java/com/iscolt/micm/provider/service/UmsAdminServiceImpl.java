package com.iscolt.micm.provider.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.iscolt.micm.provider.api.UmsAdminService;
import com.iscolt.micm.provider.entity.UmsAdmin;
import com.iscolt.micm.provider.repository.UmsAdminRepository;
import com.iscolt.micm.provider.service.fallback.UmsAdminServiceFallback;
import com.iscolt.micm.provider.utils.BaiduAiUtil;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 用户管理服务
 *
 * @Author: https://github.com/isColt
 * @Date: 3/30/2020
 * @Description: com.iscolt.micm.provider.service
 * @version: v1.0.0
 */
@Service(version = "1.0.0")
public class UmsAdminServiceImpl implements UmsAdminService {

    @Resource
    private UmsAdminRepository umsAdminRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Resource
    private BaiduAiUtil baiduAiUtil;

    @Override
    public UmsAdmin insert(UmsAdmin umsAdmin) {
        return umsAdminRepository.save(umsAdmin);
    }

    @Override
    @SentinelResource(value = "getByUsername", fallback = "getByUsernameFallback", fallbackClass = UmsAdminServiceFallback.class)
    public UmsAdmin get(String username) {
//        if ("admin".equals(username)) {
//            throw new IllegalArgumentException("invoke args");
//        }
        return umsAdminRepository.findByUsername(username);
    }

    @Override
    public UmsAdmin getById(Integer id) {
        return umsAdminRepository.findById(id).get();
    }

    @Override
    public UmsAdmin update(UmsAdmin umsAdmin) {
        UmsAdmin oldUmsAdmin = get(umsAdmin.getUsername());

        // TODO 更新点其他东西,头像额外设置
        oldUmsAdmin.setAvatar(umsAdmin.getAvatar());

        return umsAdminRepository.save(oldUmsAdmin);
    }

    @Override
    public UmsAdmin modifyPassword(String username, String passwrod) {
        UmsAdmin umsAdmin = get(username);
        umsAdmin.setPassword(passwordEncoder.encode(passwrod));
        return umsAdminRepository.save(umsAdmin);
    }

    @Override
    public UmsAdmin modifyAvatar(String username, String path) {
        UmsAdmin umsAdmin = get(username);
        umsAdmin.setAvatar(path);
        return umsAdminRepository.save(umsAdmin);
    }

    @Override
    public UmsAdmin modifyFace(String username, String path) {
        UmsAdmin umsAdmin = get(username);
        // 拿用户id, 用于百度云AI
        String userId = String.valueOf(umsAdmin.getId());

        // 判断是否已经注册面部信息
        Boolean aBoolean = baiduAiUtil.faceExist(userId);
        String imgBase64 = imgBase64(path);
        // true 更新
        if (aBoolean) {
            baiduAiUtil.faceUpdate(userId, imgBase64);
        }

        // false 注册
        else {
            baiduAiUtil.faceRegister(userId, imgBase64);
        }

        umsAdmin.setFace(path);
        return umsAdminRepository.save(umsAdmin);
    }

    /**
     * 将网络图片转成Base64码， 此方法可以解决解码后图片显示不完整的问题
     *
     * @param imgURL
     * @return
     */
    public static String imgBase64(String imgURL) {
        ByteArrayOutputStream outPut = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        try {
            // 创建URL
            URL url = new URL(null, imgURL, new sun.net.www.protocol.https.Handler());
            // 创建链接
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10 * 1000);

            if(conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return "fail";//连接失败/链接失效/图片不存在
            }
            InputStream inStream = conn.getInputStream();
            int len = -1;
            while ((len = inStream.read(data)) != -1) {
                outPut.write(data, 0, len);
            }
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(outPut.toByteArray());
    }

    // TODO 在这里可以添加初始化用户对象的方法, 也可以在对象类中写, 待定..
}
