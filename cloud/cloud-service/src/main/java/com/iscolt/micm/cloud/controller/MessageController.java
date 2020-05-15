package com.iscolt.micm.cloud.controller;

import com.iscolt.micm.cloud.producer.MessageProducer;
import com.iscolt.micm.commons.dto.ResponseResult;
import com.iscolt.micm.commons.model.dto.ResponseCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/3/2020
 * @see: com.iscolt.micm.cloud.controller
 * @version: v1.0.0
 */
@RestController
@RequestMapping(value = "cloud/message")
public class MessageController {

    @Resource
    private MessageProducer producer;

//    @PostMapping(value = "admin/login/log")
//    public ResponseResult<Void> sendAdminLoginLog(@RequestBody UmsAdminLoginLogDTO dto) {
//        boolean flag = producer.sendAdminLoginLog(dto);
//        if (flag) {
//            return new ResponseResult<Void>(ResponseCode.OK.code(), "管理员登录日志发送成功");
//        }
//
//        else {
//            return new ResponseResult<Void>(ResponseCode.FAIL.code(), "管理员登录日志发送失败");
//        }
//    }

    @PostMapping(value = "email/code")
    public ResponseResult<Void> sendEmailCode(@RequestBody String email) {
        boolean flag = producer.sendEmail(email);
        if (flag) {
            return new ResponseResult<Void>(ResponseCode.OK.code(), "验证码已发送");
        }

        else {
            return new ResponseResult<Void>(ResponseCode.FAIL.code(), "验证码发送失败");
        }
    }

}
