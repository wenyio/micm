package com.iscolt.micm.business.dto.params;

import lombok.Data;

import java.io.Serializable;

/**
 * 个人密码修改
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/1/2020
 * @see: com.iscolt.micm.business.dto
 * @version: v1.0.0
 */
@Data
public class PasswordParam implements Serializable {

    private static final long serialVersionUID = 3849939876942452391L;
    private String username;
    private String oldPassword;
    private String newPassword;

}
