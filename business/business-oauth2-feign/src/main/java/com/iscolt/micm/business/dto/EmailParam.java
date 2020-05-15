package com.iscolt.micm.business.dto;

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
 * @see: com.iscolt.micm.business.dto
 * @version: v1.0.0
 */
@Data
public class EmailParam implements Serializable {

    private static final long serialVersionUID = -5977974670893627478L;
    private String username;
    private String email;
    private String captcha;
}
