package com.iscolt.micm.cloud.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/14/2020
 * @see: com.iscolt.micm.cloud.dto
 * @version: v1.0.0
 */
@Data
public class SysLogDTO implements Serializable {

    private static final long serialVersionUID = 8200763247413073171L;
    private Integer id;

    private Integer userId;

    private String ip;

    private String address;

    /**
     * 浏览器登录类型
     */
    private String userAgent;

    /**
     * 日志内容
     */
    private String content;

    /**
     * 日志类型
     */
    private String Type;

    /**
     * 创建时间
     */
    private Date createTime;
}
