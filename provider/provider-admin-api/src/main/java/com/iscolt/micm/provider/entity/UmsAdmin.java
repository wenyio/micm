package com.iscolt.micm.provider.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户
 *
 * @Author: https://github.com/isColt
 * @Date: 3/30/2020
 * @Description: com.iscolt.micm.provider.entity
 * @version: v1.0.0
 */
@Data
@Entity(name = "ums_admin")
public class UmsAdmin implements Serializable {

    private static final long serialVersionUID = -2959175696834504488L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    /**
     * 头像
     */
    @Column
    private String avatar;

    /**
     * 人脸照片
     */
    @Column
    private String face;

    @Column
    private String username;

    @Column
    private String password;

    /**
     * 最后登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "login_time")
    private Date loginTime;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;
}
