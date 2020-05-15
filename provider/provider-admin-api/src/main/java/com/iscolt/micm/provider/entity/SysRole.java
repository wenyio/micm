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
@Table(name = "sys_role", schema = "micm", catalog = "")
public class SysRole extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -7701240463698321086L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "parent_id")
    private int parentId;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "enname")
    private String enname;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "tenant_id")
    private int tenantId;

}
