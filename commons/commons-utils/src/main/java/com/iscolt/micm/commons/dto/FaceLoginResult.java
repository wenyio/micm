package com.iscolt.micm.commons.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/4/2020
 * @see: com.iscolt.micm.commons.dto
 * @version: v1.0.0
 */
@Data
public class FaceLoginResult implements Serializable {

    private static final long serialVersionUID = 4349315230480879257L;

    /**
     * 二维码使用状态
     *   -1: 未使用
     *    0: 失败
     *    1: 登录成功(返回用户id和token)
     */
    private String state;

    /**
     * 登录信息
     */
    private String token;

    /**
     * 用户ID
     */
    private String userId;
}
