package com.iscolt.micm.business.dto.params;

import lombok.Data;

import java.io.Serializable;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/3
 * @see: com.iscolt.micm.business.dto.params
 * @version: v1.0.0
 */
@Data
public class UserRoleParam implements Serializable {
    private static final long serialVersionUID = 7477015304061271766L;
    private Integer userId;
    private Integer roleId;
}
