package com.iscolt.micm.provider.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * xx
 * <p>
 * Description: 注意不要和service搞混
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/9
 * @see: com.iscolt.micm.provider.entity
 * @version: v1.0.0
 */
@Entity
@Table(name = "sys_tenant_service", schema = "micm", catalog = "")
public class SysTenantService implements Serializable {
    private static final long serialVersionUID = -6619570202209526L;
    private int id;
    private int tenantId;
    private int serviceId;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp renewalDate;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp expirationDate;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp freezeDate;
    private boolean status;

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
    @Column(name = "tenant_id")
    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    @Basic
    @Column(name = "service_id")
    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
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
    @Column(name = "freeze_date")
    public Timestamp getFreezeDate() {
        return freezeDate;
    }

    public void setFreezeDate(Timestamp freezeDate) {
        this.freezeDate = freezeDate;
    }

    @Basic
    @Column(name = "status")
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysTenantService that = (SysTenantService) o;
        return id == that.id &&
                tenantId == that.tenantId &&
                serviceId == that.serviceId &&
                status == that.status &&
                Objects.equals(renewalDate, that.renewalDate) &&
                Objects.equals(expirationDate, that.expirationDate) &&
                Objects.equals(freezeDate, that.freezeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tenantId, serviceId, renewalDate, expirationDate, freezeDate, status);
    }
}
