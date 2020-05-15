package com.iscolt.micm.cloud.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/11
 * @see: com.iscolt.micm.cloud.dto
 * @version: v1.0.0
 */
@Data
public class EmailDTO implements Serializable {

    private static final long serialVersionUID = -3757888934998513496L;
    private String username;
    private String email;
}
