package com.iscolt.micm.provider.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * 系统租户
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/9
 * @see: com.iscolt.micm.provider.entity
 * @version: v1.0.0
 */
@Data
@Entity
@Table(name = "sys_tenant", schema = "micm", catalog = "")
public class SysTenant implements Serializable {
    private static final long serialVersionUID = -7335242819463981290L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "icon")
    private String icon;
    // 租户地区
    @Basic
    @Column(name = "tenant_area")
    private String tenantArea;
    // 租户地址
    @Basic
    @Column(name = "tenant_address")
    private String tenantAddress;
    // 营业执照
    @Basic
    @Column(name = "business_license_id")
    private String businessLicenseId;
    // 法定代表人
    @Basic
    @Column(name = "legal_representative")
    private String legalRepresentative;
    // 租户电话
    @Basic
    @Column(name = "tenant_phone")
    private String tenantPhone;
    // 邮箱
    @Basic
    @Column(name = "mailbox")
    private String mailbox;
    // 规模
    @Basic
    @Column(name = "tenant_size")
    private String tenantSize;
    // 余额
    @Basic
    @Column(name = "balance")
    private BigDecimal balance;
    // 续期时间
    @Basic
    @Column(name = "renewal_date")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp renewalDate;
    // 到期时间
    @Basic
    @Column(name = "expiration_date")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp expirationDate;
    /**
     * 审核状态
     * 0 未审核 1 通过 -1 未通过
     */
    @Basic
    @Column(name = "audit_state")
    private String auditState;
    // 状态
    @Basic
    @Column(name = "status")
    private Boolean status;
    @Basic
    @Column(name = "created")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp created;
    @Basic
    @Column(name = "updated")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp updated;

    @Override
    public int hashCode() {
        return Objects.hash(id, name, userId, icon, tenantArea, tenantAddress, businessLicenseId, legalRepresentative, tenantPhone, mailbox, tenantSize, balance, renewalDate, expirationDate, auditState, status, created, updated);
    }

    /**
     * 初始化执行
     */
    @PrePersist
    protected void prePersist() {
        if (created == null) {
            created = new Timestamp(new Date().getTime());
        }

        if (updated == null) {
            updated = new Timestamp(new Date().getTime());
        }

        if (balance == null) {
            balance = BigDecimal.ZERO;
        }
    }

    /**
     * 更新时执行
     */
    @PreUpdate
    protected void preUpdate() {
        updated = new Timestamp(new Date().getTime());
    }
}
