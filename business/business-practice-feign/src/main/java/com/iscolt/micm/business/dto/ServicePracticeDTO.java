package com.iscolt.micm.business.dto;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/13
 * @see: com.iscolt.micm.business.dto
 * @version: v1.0.0
 */
@Data
public class ServicePracticeDTO implements Serializable {
    private static final long serialVersionUID = -6147000929752885571L;
    private long id;
    private String title;
    private String icon;
    private Integer userId;
    private Long categoryId;
    private Long organizationId;
    private Timestamp begin;
    private Timestamp end;
    private Integer integral;
    private String organization;
    private String level;
    private String address;
    private String description;
    private String url;
    private Boolean status;
}
