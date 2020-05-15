package com.iscolt.micm.business.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录参数
 *
 * @Author: https://github.com/isColt
 * @Date: 3/29/2020
 * @Description: com.iscolt.micm.business.dto
 * @version: v1.0.0
 */
@Data
public class LoginParam implements Serializable {

    private static final long serialVersionUID = 6375099926323472400L;
    private String username;
    private String password;
}
