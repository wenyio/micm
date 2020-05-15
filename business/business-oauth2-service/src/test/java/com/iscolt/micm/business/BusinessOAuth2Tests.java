package com.iscolt.micm.business;

import okhttp3.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * oauth2 测试类
 *
 * @Author: https://github.com/isColt
 * @Date: 3/29/2020
 * @Description: com.iscolt.micm.business
 * @version: v1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BusinessOAuth2Tests {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 测试密码编码器
     */
    @Test
    public void testPasswordEncoder() {
        System.out.println(bCryptPasswordEncoder.encode("123456"));
    }

}
