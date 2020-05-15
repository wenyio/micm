package com.iscolt.micm.message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/10
 * @see: com.iscolt.micm.cloud
 * @version: v1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailTests {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Test
    public void contextLoads() {

        SimpleMailMessage message = new SimpleMailMessage();//简单邮件
        //邮件设置
        message.setSubject("通知>>测试通知");
        message.setText("测试内容内容");

        message.setTo("1250074822@qq.com");
        message.setFrom(applicationContext.getEnvironment().getProperty("spring.mail.username"));
        mailSender.send(message);
        System.out.println("成功");
    }

    @Test
    public void test02() throws Exception{
        //创建一个复杂的消息邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        //邮件设置
        helper.setSubject("通知>>测试通知");
        helper.setText("<b style='color:red'>测试内容内容!</b>",true);//开启html格式

        helper.setTo("1250074822@qq.com");
        helper.setFrom(applicationContext.getEnvironment().getProperty("spring.mail.username"));

        //上传文件
        helper.addAttachment("1.jpg",new File("F:\\图片\\picture\\1.jpg"));
        helper.addAttachment("2.jpg",new File("F:\\图片\\picture\\2.jpg"));
        mailSender.send(mimeMessage);
        System.out.println("成功");
    }

}
