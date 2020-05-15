package com.iscolt.micm.business.dto.params;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 批量的数据
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
public class RolePermissionListParam implements Serializable {

    private static final long serialVersionUID = -2229469201215807705L;
    private Integer roleId;
    private List<Integer> list;
}
