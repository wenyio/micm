package com.iscolt.micm.provider.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
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
@Data
@Entity
@Table(name = "service_user_practice")
public class ServiceUserPractice implements Serializable {
    private static final long serialVersionUID = -2480784677058641576L;
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "tenant_id")
    private int tenantId;
    @Basic
    @Column(name = "practice_id")
    private long practiceId;
    @Basic
    @Column(name = "status")
    private Integer status;
    @Basic
    @Column(name = "created")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp created;

    /**
     * 初始化执行
     */
    @PrePersist
    protected void prePersist() {
        if (created == null) {
            created = new Timestamp(new Date().getTime());
        }
        if (status == null) {
            status = 0;
        }
    }
}
