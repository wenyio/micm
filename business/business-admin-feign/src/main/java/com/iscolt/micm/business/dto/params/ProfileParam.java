package com.iscolt.micm.business.dto.params;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 个人信息修改
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
public class ProfileParam implements Serializable {

    private static final long serialVersionUID = -8954287265991121091L;

    private Integer id;

    private String username;

    private String nickname;

    private String phone;

    private String email;

}
