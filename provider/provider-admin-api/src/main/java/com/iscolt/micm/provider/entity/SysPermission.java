package com.iscolt.micm.provider.entity;

import com.iscolt.micm.commons.model.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/9/2020
 * @see: com.iscolt.micm.provider.entity
 * @version: v1.0.0
 */
@Data
@Entity
@Table(name = "sys_permission", schema = "micm", catalog = "")
public class SysPermission extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 3612369981861302338L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    @Column(name = "parent_id")
    private int parentId;
    @Column(name = "tenant_id")
    private int tenantId;
    @Basic
    @Column(name = "name")
    private String name;
    /**
     * 权限类型 0菜单, 1API, 默认0
     */
    @Basic
    @Column(name = "type")
    private byte type;
    @Basic
    @Column(name = "description")
    private String description;
    /**
     * 租户可见性 0: 不可见 1 可见, 默认不可见
     */
    @Basic
    @Column(name = "visible")
    private Boolean visible;
}
