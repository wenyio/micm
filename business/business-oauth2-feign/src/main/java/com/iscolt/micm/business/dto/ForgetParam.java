package com.iscolt.micm.business.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 忘记密码
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/11
 * @see: com.iscolt.micm.business.dto
 * @version: v1.0.0
 */
@Data
public class ForgetParam implements Serializable {

    private static final long serialVersionUID = 7017859384934000787L;
    private String email;
    /**
     * 验证码
     */
    private String captcha;
    private String newPassword;
}
