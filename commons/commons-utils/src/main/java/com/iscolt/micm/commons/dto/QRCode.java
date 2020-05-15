package com.iscolt.micm.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@NoArgsConstructor
public class QRCode implements Serializable {

    private static final long serialVersionUID = 2895985695865597718L;

    /**
     * 随机生成码
     */
    private String code;

    /**
     * Base64 二维码文件
     */
    private String file;

}
