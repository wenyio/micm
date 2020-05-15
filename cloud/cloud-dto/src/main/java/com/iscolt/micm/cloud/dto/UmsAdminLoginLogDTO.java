package com.iscolt.micm.cloud.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/3/2020
 * @see: com.iscolt.micm.cloud.dto
 * @version: v1.0.0
 */
@Data
public class UmsAdminLoginLogDTO implements Serializable {

    private static final long serialVersionUID = -1428040024581972463L;

    private Integer id;

    private Integer adminId;

    /**
     * 创建时间
     */
    private Date createTime;

    private String ip;

    private String address;

    /**
     * 浏览器登录类型
     */
    private String userAgent;
}
