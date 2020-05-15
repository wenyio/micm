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
 * @date: 2020/5/6
 * @see: com.iscolt.micm.business.dto.params
 * @version: v1.0.0
 */
@Data
public class PermissionApiParam implements Serializable {

    private static final long serialVersionUID = 84290165263925982L;
    private Integer id;
    private Integer parentId;
    private String name;
    private String description;
    private String url;
}
