package com.iscolt.micm.business.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录信息
 *
 * @Author: https://github.com/isColt
 * @Date: 3/30/2020
 * @Description: com.iscolt.micm.business.dto
 * @version: v1.0.0
 */
@Data
public class LoginInfo implements Serializable {

    private static final long serialVersionUID = 4116313558232347641L;
    private String name;
    private String avatar;
}
