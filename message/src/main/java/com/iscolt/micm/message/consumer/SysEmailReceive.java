package com.iscolt.micm.message.consumer;

import com.iscolt.micm.commons.utils.MapperUtils;
import com.iscolt.micm.message.dto.EmailDTO;
import com.iscolt.micm.provider.entity.SysLog;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 邮件消费者
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/14/2020
 * @see: com.iscolt.micm.message.consumer
 * @version: v1.0.0
 */
@Service
public class SysEmailReceive {

    // 验证码失效时间
    private final static Integer VERIFICATION_CODE_EXPIRATION_TIME = 5;

    @Resource
    private ConfigurableApplicationContext applicationContext;

    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @StreamListener("sys-email-topic")
    public void receiveSendEmail(String emailJson) throws Exception {
        EmailDTO email = MapperUtils.json2pojo(emailJson, EmailDTO.class);
        String code = createMsgCode();
        // 将email：code 存入redis
        redisTemplate.delete(email.getEmail());
        redisTemplate.boundValueOps(email.getEmail()).set(code, VERIFICATION_CODE_EXPIRATION_TIME, TimeUnit.MINUTES); // code, 失效时间, 单位
        sendEmail("校织网-账号验证"
                , "尊的用户您好！感谢您的使用！您此次的邮箱验证码是："
                + code
                + "。（使用后失效）有效期"
                + VERIFICATION_CODE_EXPIRATION_TIME
                + "分钟，请妥善保管。"
                , email.getEmail());
    }

    /**
     * 发送普通邮件
     * @param subject
     * @param body
     * @param to
     */
    @Async
    public void sendEmail(String subject, String body, String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(applicationContext.getEnvironment().getProperty("spring.mail.username"));
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }

    /**
     * 生成随机的6位数验证码
     *
     * @return
     */
    private static String createMsgCode() {
        int n = 6;
        StringBuilder code = new StringBuilder();
        Random ran = new Random();
        for (int i = 0; i < n; i++) {
            code.append(Integer.valueOf(ran.nextInt(10)).toString());
        }
        return code.toString();
    }
}
