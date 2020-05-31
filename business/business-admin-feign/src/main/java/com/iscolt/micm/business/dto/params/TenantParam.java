package com.iscolt.micm.business.dto.params;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/30
 * @see: com.iscolt.micm.business.dto.params
 * @version: v1.0.0
 */
@Data
public class TenantParam implements Serializable {
    private static final long serialVersionUID = -268291274648355760L;
    private int id;
    private String name;
    private int userId;
    private String icon;
    // 租户地区
    private String tenantArea;
    // 租户地址
    private String tenantAddress;
    // 营业执照
    private String businessLicenseId;
    // 法定代表人
    private String legalRepresentative;
    // 租户电话
    private String tenantPhone;
    // 邮箱
    private String mailbox;
    // 规模
    private String tenantSize;
    // 审核状态 0 未审核 1 通过 -1 未通过
    private String auditState;
    // 状态
    private Boolean status;
}
