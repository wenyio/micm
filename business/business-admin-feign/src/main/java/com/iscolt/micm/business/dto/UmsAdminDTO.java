package com.iscolt.micm.business.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/2/2020
 * @see: com.iscolt.micm.business.dto
 * @version: v1.0.0
 */
@Data
public class UmsAdminDTO implements Serializable {

    private Integer id;

    private String avatar;

    private String username;

    private Date loginTime;

    private Date createTime;
}
