package com.iscolt.micm.business.dto.params;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色权限
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
public class RolePermissionParam implements Serializable {

    private static final long serialVersionUID = 531235243813586577L;
    private Integer roleId;
    private Integer permissionId;
}
