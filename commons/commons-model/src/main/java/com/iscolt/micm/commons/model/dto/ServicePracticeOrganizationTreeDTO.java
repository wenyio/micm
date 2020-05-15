package com.iscolt.micm.commons.model.dto;

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
 * @date: 2020/5/12
 * @see: com.iscolt.micm.commons.model.dto
 * @version: v1.0.0
 */
@Data
public class ServicePracticeOrganizationTreeDTO implements Serializable {
    private static final long serialVersionUID = -385189467874834717L;
    private long id;
    private long parentId;
    private String name;
    private String ename;
    private String description;
    private List<ServicePracticeOrganizationTreeDTO> children;
}
