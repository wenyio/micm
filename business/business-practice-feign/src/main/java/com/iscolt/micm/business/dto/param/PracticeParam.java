package com.iscolt.micm.business.dto.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/6/3
 * @see: com.iscolt.micm.business.dto.param
 * @version: v1.0.0
 */
@Data
public class PracticeParam implements Serializable {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private long id;
    private String title;
    private String icon;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long categoryId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long organizationId;
    private Timestamp begin;
    private Timestamp end;
    private Integer integral;
    private String organization;
    private String address;
    private String description;
}
