package com.iscolt.micm.business.dto.params;

import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;

/**
 * xx
 * <p>
 * Description: 用于查询租户所订阅的服务
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/31
 * @see: com.iscolt.micm.business.dto.params
 * @version: v1.0.0
 */
@Data
public class TenantServiceParam implements Serializable {

    private Integer serviceId;
    private Integer tenantId;
}
