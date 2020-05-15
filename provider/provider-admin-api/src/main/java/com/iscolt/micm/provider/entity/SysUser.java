package com.iscolt.micm.provider.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iscolt.micm.commons.model.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Basic;
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
 * @date: 4/6/2020
 * @see: com.iscolt.micm.provider.entity
 * @version: v1.0.0
 */
@Data
@Entity
@Table(name = "sys_user", schema = "micm", catalog = "")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysUser extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -6440226959556058019L;

    /**
     * ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * 昵称
     */
    @Basic
    @Column(name = "nickname")
    private String nickname;

    /**
     * 用户名
     */
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "email")
    private String email;

    /**
     * 账号
     */
    @Basic
    @Column(name = "account")
    private String account;

    /**
     * 身份等级
     */
    @Basic
    @Column(name = "level")
    private String level;

    /**
     * 头像
     */
    @Basic
    @Column(name = "avatar")
    private String avatar;

    /**
     * 人脸图片
     */
    @Basic
    @Column(name = "face")
    private String face;

    /**
     * 人脸ID
     */
    @Basic
    @Column(name = "face_id")
    private String faceId;

    /**
     * 租户ID
     */
    @Basic
    @Column(name = "tenant_id")
    private Integer tenantId;

    /**
     * 租户名
     */
    @Basic
    @Column(name = "tenant_name")
    private String tenantName;

    /**
     * 状态 1可用; 0不可用;
     */
    @Basic
    @Column(name = "status")
    private boolean status;

    /**
     * 登录时间
     */
    @Basic
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "login_time")
    private Timestamp loginTime;


    @Override
    protected void prePersist() {
        super.prePersist();
        this.status = true;
    }
}
