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
 * @date: 2020/5/2
 * @see: com.iscolt.micm.business.dto.params
 * @version: v1.0.0
 */
@Data
public class RoleParam implements Serializable {

    private static final long serialVersionUID = -41512284186376239L;
    private Integer id;
    private Integer parentId;
    private String name;
    private String enname;
    private String description;
}
