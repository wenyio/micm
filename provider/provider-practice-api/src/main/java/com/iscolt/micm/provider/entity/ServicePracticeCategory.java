package com.iscolt.micm.provider.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/9
 * @see: com.iscolt.micm.provider.entity
 * @version: v1.0.0
 */
@Entity
@Table(name = "service_practice_category")
public class ServicePracticeCategory implements Serializable {
    private static final long serialVersionUID = -989523436516668987L;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private long parentId;
    private Integer tenantId;
    private String name;
    private String ename;
    private String description;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp created;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "parent_id")
    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "tenant_id")
    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
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
    @Column(name = "ename")
    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "created")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServicePracticeCategory that = (ServicePracticeCategory) o;
        return id == that.id &&
                parentId == that.parentId &&
                Objects.equals(tenantId, that.tenantId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(ename, that.ename) &&
                Objects.equals(description, that.description) &&
                Objects.equals(created, that.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parentId, tenantId, name, ename, description, created);
    }

    /**
     * 初始化执行
     */
    @PrePersist
    protected void prePersist() {
        if (created == null) {
            created = new Timestamp(new Date().getTime());
        }
    }
}
