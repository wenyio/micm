package com.iscolt.micm.message.dto;

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
 * @see: com.iscolt.micm.message.dto
 * @version: v1.0.0
 */
@Data
public class EmailDTO implements Serializable {

    private static final long serialVersionUID = -5774106909818748258L;
    private String email;
}
