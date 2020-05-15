package com.iscolt.micm.provider.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/14/2020
 * @see: com.iscolt.micm.provider.entity
 * @version: v1.0.0
 */
@Data
@Entity
@Table(name = "sys_log", schema = "micm", catalog = "")
public class SysLog implements Serializable {

    private static final long serialVersionUID = 4765012840357237633L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column
    private String ip;
    @Column
    private String address;
    /**
     * 浏览器登录类型
     */
    @Column(name = "user_agent")
    private String userAgent;
    /**
     * 日志内容
     */
    private String content;
    /**
     * 日志类型
     */
    private String type;
    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp created;
}
