package com.iscolt.micm.commons.model.dto;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 权限树
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/1
 * @see: com.iscolt.micm.commons.model.dto
 * @version: v1.0.0
 */
@Data
public class SysPermissionTreeDTO implements Serializable {
    private static final long serialVersionUID = -7425025518442643631L;
    private int id;
    private int parentId;
    private int tenantId;
    private String name;
    private byte type;
    private String description;
    private List<SysPermissionTreeDTO> children;
}
