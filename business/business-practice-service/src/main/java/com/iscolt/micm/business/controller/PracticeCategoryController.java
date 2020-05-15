package com.iscolt.micm.business.controller;

import com.iscolt.micm.commons.dto.ResponseResult;
import com.iscolt.micm.commons.model.dto.ResponseCode;
import com.iscolt.micm.provider.api.ServicePracticeCategoryService;
import com.iscolt.micm.provider.api.SysUserService;
import com.iscolt.micm.provider.entity.ServicePracticeCategory;
import com.iscolt.micm.provider.entity.SysUser;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 活动分类
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
@RequestMapping(value = "practice/category")
public class PracticeCategoryController {

    @Reference(version = "1.0.0")
    private SysUserService sysUserService;

    @Reference(version = "1.0.0")
    private ServicePracticeCategoryService servicePracticeCategoryService;

    /**
     * 获取全部数据，非tree
     * @return
     */
    @GetMapping(value = "")
    public ResponseResult<List<ServicePracticeCategory>> list() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = sysUserService.get(authentication.getName());
        List<ServicePracticeCategory> servicePracticeCategories = servicePracticeCategoryService.listByTenantId(sysUser.getTenantId());
        return new ResponseResult<List<ServicePracticeCategory>>(ResponseCode.OK.code(), ResponseCode.OK.message(), servicePracticeCategories);
    }
}
