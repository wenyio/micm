package com.iscolt.micm.commons.provider.view;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色可以访问的API
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/10/2020
 * @see: com.iscolt.micm.provider.entity.view
 * @version: v1.0.0
 */
@Data
public class SysRoleApiView implements Serializable {
    private static final long serialVersionUID = 499001608992643564L;

    private Integer id;

    /**
     * 角色名
     */
    private String enname;

    /**
     * API地址
     */
    private String url;

    public SysRoleApiView() {
    }

    public SysRoleApiView(Integer id, String enname, String url) {
        this.id = id;
        this.enname = enname;
        this.url = url;
    }
}
