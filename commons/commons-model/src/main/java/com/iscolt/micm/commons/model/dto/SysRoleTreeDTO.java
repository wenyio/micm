package com.iscolt.micm.commons.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/4/30
 * @see: com.iscolt.micm.commons.model.dto
 * @version: v1.0.0
 */
@Data
public class SysRoleTreeDTO implements Serializable {
    private static final long serialVersionUID = 531901376369382078L;
    private int id;
    private int parentId;
    private int tenantId;
    private String name;
    private String enname;
    private String description;
    private List<SysRoleTreeDTO> children;
}
