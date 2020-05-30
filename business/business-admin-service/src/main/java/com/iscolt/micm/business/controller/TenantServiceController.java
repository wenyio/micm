package com.iscolt.micm.business.controller;

import com.iscolt.micm.provider.api.SysTenantServiceService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/16
 * @see: com.iscolt.micm.business.controller
 * @version: v1.0.0
 */
@RestController
@RequestMapping(value = "admin/tenant/service")
public class TenantServiceController {

    @Reference(version = "1.0.0")
    private SysTenantServiceService sysTenantServiceService;
}
