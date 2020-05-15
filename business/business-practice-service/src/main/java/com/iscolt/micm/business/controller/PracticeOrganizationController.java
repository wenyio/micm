package com.iscolt.micm.business.controller;

import com.iscolt.micm.commons.dto.ResponseResult;
import com.iscolt.micm.commons.model.dto.ResponseCode;
import com.iscolt.micm.provider.api.ServicePracticeOrganizationService;
import com.iscolt.micm.provider.api.SysUserService;
import com.iscolt.micm.provider.entity.ServicePracticeCategory;
import com.iscolt.micm.provider.entity.ServicePracticeOrganization;
import com.iscolt.micm.provider.entity.SysUser;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/13
 * @see: com.iscolt.micm.business.controller
 * @version: v1.0.0
 */
@RestController
@RequestMapping(value = "practice/organization")
public class PracticeOrganizationController {

    @Reference(version = "1.0.0")
    private SysUserService sysUserService;

    @Reference(version = "1.0.0")
    private ServicePracticeOrganizationService servicePracticeOrganizationService;

    /**
     * 获取全部数据，非tree
     * @return
     */
    @GetMapping(value = "")
    public ResponseResult<List<ServicePracticeOrganization>> list() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = sysUserService.get(authentication.getName());
        List<ServicePracticeOrganization> servicePracticeOrganizations = servicePracticeOrganizationService.listByTenantId(sysUser.getTenantId());
        return new ResponseResult<List<ServicePracticeOrganization>>(ResponseCode.OK.code(), ResponseCode.OK.message(), servicePracticeOrganizations);
    }
}
