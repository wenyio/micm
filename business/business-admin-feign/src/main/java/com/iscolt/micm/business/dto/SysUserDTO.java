package com.iscolt.micm.business.dto;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 用户信息 TODO 有需要再修改
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/6/2020
 * @see: com.iscolt.micm.business.dto
 * @version: v1.0.0
 */
@Data
public class SysUserDTO implements Serializable {

    private int id;
    private String avatar;
    private String account;
    private String nickname;
    private String username;
    private String phone;
    private String email;
    private String level;
    private String face;
    private String faceId;
    private String tenantName;
    private Timestamp loginTime;
    private Timestamp created;
    private Timestamp updated;

}
