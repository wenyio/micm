package com.iscolt.micm.commons.model.dto;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
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
public class SysPermissionApiTreeDTO implements Serializable {

    private static final long serialVersionUID = 4353810633870504679L;
    private int id;
    private int parentId;
    private String name;
    private String description;
    private String url;
    List<SysPermissionApiTreeDTO> children;
}
