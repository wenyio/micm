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
import java.sql.Timestamp;
import java.util.Date;
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
@Data
@Entity
@Table(name = "sys_tenant_service", schema = "micm", catalog = "")
public class SysTenantService implements Serializable {
    private static final long serialVersionUID = -6619570202209526L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    @Column(name = "tenant_id")
    private int tenantId;
    @Basic
    @Column(name = "service_id")
    private int serviceId;
    /**
     * 续费时间
     */
    @Basic
    @Column(name = "renewal_date")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp renewalDate;
    /**
     * 到期时间
     */
    @Basic
    @Column(name = "expiration_date")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp expirationDate;
    /**
     * 冻结时间
     */
    @Basic
    @Column(name = "freeze_date")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp freezeDate;
    @Basic
    @Column(name = "status")
    private boolean status;

    /**
     * 初始化执行
     */
    @PrePersist
    protected void prePersist() {
        if (renewalDate == null) {
            renewalDate = new Timestamp(new Date().getTime());
        }
    }
}
