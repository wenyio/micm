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
public class ServicePracticeCategoryTreeDTO implements Serializable {
    private static final long serialVersionUID = -5212553063536358117L;
    private long id;
    private long parentId;
    private String name;
    private String ename;
    private String description;
    private List<ServicePracticeCategoryTreeDTO> children;
}
