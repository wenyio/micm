package com.iscolt.micm.commons.provider.view;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/4
 * @see: com.iscolt.micm.commons.provider.view
 * @version: v1.0.0
 */
@Data
public class SysPermissionApiView implements Serializable {

    private static final long serialVersionUID = 4353810633870504679L;
    private Integer id;
    private Integer parentId;
    private Integer tenantId;
    private String name;
    private String description;
    private String url;

    public SysPermissionApiView(Integer id, Integer parentId, Integer tenantId, String name, String description, String url) {
        this.id = id;
        this.parentId = parentId;
        this.tenantId = tenantId;
        this.name = name;
        this.description = description;
        this.url = url;
    }
}
