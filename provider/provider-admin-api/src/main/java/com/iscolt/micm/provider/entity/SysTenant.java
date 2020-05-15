package com.iscolt.micm.provider.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Entity
@Table(name = "sys_tenant", schema = "micm", catalog = "")
public class SysTenant implements Serializable {
    private static final long serialVersionUID = -7335242819463981290L;
    private int id;
    private String name;
    private int userId;
    private String icon;
    private String tenantArea;
    private String tenantAddress;
    private String businessLicenseId;
    private String legalRepresentative;
    private String tenantPhone;
    private String mailbox;
    private String tenantSize;
    private BigDecimal balance;
    private Timestamp renewalDate;
    private Timestamp expirationDate;
    private String auditState;
    private Boolean status;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp created;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp updated;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Basic
    @Column(name = "tenant_area")
    public String getTenantArea() {
        return tenantArea;
    }

    public void setTenantArea(String tenantArea) {
        this.tenantArea = tenantArea;
    }

    @Basic
    @Column(name = "tenant_address")
    public String getTenantAddress() {
        return tenantAddress;
    }

    public void setTenantAddress(String tenantAddress) {
        this.tenantAddress = tenantAddress;
    }

    @Basic
    @Column(name = "business_license_id")
    public String getBusinessLicenseId() {
        return businessLicenseId;
    }

    public void setBusinessLicenseId(String businessLicenseId) {
        this.businessLicenseId = businessLicenseId;
    }

    @Basic
    @Column(name = "legal_representative")
    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    @Basic
    @Column(name = "tenant_phone")
    public String getTenantPhone() {
        return tenantPhone;
    }

    public void setTenantPhone(String tenantPhone) {
        this.tenantPhone = tenantPhone;
    }

    @Basic
    @Column(name = "mailbox")
    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    @Basic
    @Column(name = "tenant_size")
    public String getTenantSize() {
        return tenantSize;
    }

    public void setTenantSize(String tenantSize) {
        this.tenantSize = tenantSize;
    }

    @Basic
    @Column(name = "balance")
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "renewal_date")
    public Timestamp getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(Timestamp renewalDate) {
        this.renewalDate = renewalDate;
    }

    @Basic
    @Column(name = "expiration_date")
    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Basic
    @Column(name = "audit_state")
    public String getAuditState() {
        return auditState;
    }

    public void setAuditState(String auditState) {
        this.auditState = auditState;
    }

    @Basic
    @Column(name = "status")
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Basic
    @Column(name = "created")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "updated")
    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysTenant sysTenant = (SysTenant) o;
        return id == sysTenant.id &&
                userId == sysTenant.userId &&
                Objects.equals(name, sysTenant.name) &&
                Objects.equals(icon, sysTenant.icon) &&
                Objects.equals(tenantArea, sysTenant.tenantArea) &&
                Objects.equals(tenantAddress, sysTenant.tenantAddress) &&
                Objects.equals(businessLicenseId, sysTenant.businessLicenseId) &&
                Objects.equals(legalRepresentative, sysTenant.legalRepresentative) &&
                Objects.equals(tenantPhone, sysTenant.tenantPhone) &&
                Objects.equals(mailbox, sysTenant.mailbox) &&
                Objects.equals(tenantSize, sysTenant.tenantSize) &&
                Objects.equals(balance, sysTenant.balance) &&
                Objects.equals(renewalDate, sysTenant.renewalDate) &&
                Objects.equals(expirationDate, sysTenant.expirationDate) &&
                Objects.equals(auditState, sysTenant.auditState) &&
                Objects.equals(status, sysTenant.status) &&
                Objects.equals(created, sysTenant.created) &&
                Objects.equals(updated, sysTenant.updated);
    }

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
    }

    /**
     * 更新时执行
     */
    @PreUpdate
    protected void preUpdate() {
        updated = new Timestamp(new Date().getTime());
    }
}
