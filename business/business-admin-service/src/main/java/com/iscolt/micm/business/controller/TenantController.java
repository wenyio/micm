package com.iscolt.micm.business.controller;

import com.iscolt.micm.commons.dto.ResponseResult;
import com.iscolt.micm.commons.model.dto.ResponseCode;
import com.iscolt.micm.provider.api.SysTenantService;
import com.iscolt.micm.provider.entity.SysService;
import com.iscolt.micm.provider.entity.SysTenant;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.data.domain.Sort.Direction.ASC;

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
@RequestMapping(value = "admin/tenants")
public class TenantController {

    @Reference(version = "1.0.0")
    private SysTenantService sysTenantService;

    @GetMapping(value = "")
    public ResponseResult<Page<SysTenant>> list(@PageableDefault(sort = {"created"}, direction = ASC) Pageable pageable) {
        Page<SysTenant> sysTenants = sysTenantService.listAll(pageable);
        return new ResponseResult<Page<SysTenant>>(ResponseCode.OK.code(), ResponseCode.OK.message(), sysTenants);
    }
}
