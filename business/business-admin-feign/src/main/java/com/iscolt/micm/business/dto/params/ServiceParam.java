package com.iscolt.micm.business.dto.params;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/16
 * @see: com.iscolt.micm.business.dto.params
 * @version: v1.0.0
 */
@Data
public class ServiceParam implements Serializable {
    private static final long serialVersionUID = -5442632162639564672L;
    private int id;
    private String name;
    private String icon;
    private String cover;
    private BigDecimal price;
    private String version;
    private String description;
    private Boolean status;
}
