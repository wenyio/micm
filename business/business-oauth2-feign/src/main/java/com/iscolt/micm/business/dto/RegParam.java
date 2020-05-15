package com.iscolt.micm.business.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 注册
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/6
 * @see: com.iscolt.micm.business.dto
 * @version: v1.0.0
 */
@Data
public class RegParam implements Serializable {
    private static final long serialVersionUID = -1364574917042290211L;
    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户名
     */
    private String username;
    private String password;
    private String phone;
    private String email;

    private String account;

    /**
     * 状态 1可用; 0不可用;
     */
    private boolean status;
}
