package com.iscolt.micm.business.controller;

import com.iscolt.micm.business.dto.ServicePracticeDTO;
import com.iscolt.micm.business.dto.param.PracticeParam;
import com.iscolt.micm.commons.dto.ResponseResult;
import com.iscolt.micm.commons.model.dto.ResponseCode;
import com.iscolt.micm.commons.utils.IdWorker;
import com.iscolt.micm.provider.api.ServicePracticeService;
import com.iscolt.micm.provider.api.SysUserService;
import com.iscolt.micm.provider.api.TenantUserInfoService;
import com.iscolt.micm.provider.entity.ServicePractice;
import com.iscolt.micm.provider.entity.SysUser;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 活动控制器
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
@RequestMapping(value = "practice")
public class PracticeController {

    @Reference(version = "1.0.0")
    private SysUserService sysUserService;

    @Reference(version = "1.0.0")
    private TenantUserInfoService tenantUserInfoService;

    @Reference(version = "1.0.0")
    private ServicePracticeService servicePracticeService;

    @Resource
    private IdWorker idWorker;

    /**
     * 全部活动
     * @return
     */
    @GetMapping(value = "")
    public ResponseResult<List<ServicePracticeDTO>> list() {
        List<ServicePracticeDTO> servicePracticeDTOS = new ArrayList<>();
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = sysUserService.get(authentication.getName());
        List<ServicePractice> servicePractices = servicePracticeService.listByTenantId(sysUser.getTenantId());
        servicePractices.forEach(item -> {
            ServicePracticeDTO servicePracticeDTO = new ServicePracticeDTO();
            BeanUtils.copyProperties(item, servicePracticeDTO);
            servicePracticeDTOS.add(servicePracticeDTO);
        });
        return new ResponseResult<List<ServicePracticeDTO>>(ResponseCode.OK.code(), ResponseCode.OK.message(), servicePracticeDTOS);
    }

    @PostMapping(value = "add")
    public ResponseResult<Void> add(@RequestBody PracticeParam practiceParam) {
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = sysUserService.get(authentication.getName());

        ServicePractice servicePractice = new ServicePractice();
        BeanUtils.copyProperties(practiceParam, servicePractice);
        servicePractice.setTenantId(sysUser.getTenantId());
        servicePractice.setUserId(sysUser.getId());
        servicePractice.setId(idWorker.nextId());
        servicePracticeService.create(servicePractice);
        return new ResponseResult<Void>(ResponseCode.OK.code(), ResponseCode.OK.message());
    }

    /**
     * 根据id获取
     * @return
     */
    @GetMapping(value = "{id}")
    public ResponseResult<ServicePracticeDTO> getById(@PathVariable(value = "id") long id) {
        ServicePractice servicePractice = servicePracticeService.getById(id);
        ServicePracticeDTO servicePracticeDTO = new ServicePracticeDTO();
        BeanUtils.copyProperties(servicePractice, servicePracticeDTO);
        return new ResponseResult<ServicePracticeDTO>(ResponseCode.OK.code(), ResponseCode.OK.message(), servicePracticeDTO);
    }

    /**
     * 搜索
     * @param key
     * @return
     */
    @GetMapping(value = "search/{key}")
    public ResponseResult<List<ServicePracticeDTO>> search(@PathVariable(value = "key") String key) {
        List<ServicePracticeDTO> servicePracticeDTOS = new ArrayList<>();
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = sysUserService.get(authentication.getName());
        List<ServicePractice> servicePractices = servicePracticeService.search(sysUser.getTenantId(), key);
        servicePractices.forEach(item -> {
            ServicePracticeDTO servicePracticeDTO = new ServicePracticeDTO();
            BeanUtils.copyProperties(item, servicePracticeDTO);
            servicePracticeDTOS.add(servicePracticeDTO);
        });
        return new ResponseResult<List<ServicePracticeDTO>>(ResponseCode.OK.code(), ResponseCode.OK.message(), servicePracticeDTOS);
    }

    /**
     * 推荐
     * @return
     */
    @GetMapping(value = "recommend")
    public ResponseResult<List<ServicePracticeDTO>> recommend() {
        List<ServicePracticeDTO> servicePracticeDTOS = new ArrayList<>();
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = sysUserService.get(authentication.getName());
        List<ServicePractice> servicePractices = servicePracticeService.recommend(sysUser.getTenantId(), tenantUserInfoService.getByUserId(sysUser.getId()).getOrganizationId());
        servicePractices.forEach(item -> {
            ServicePracticeDTO servicePracticeDTO = new ServicePracticeDTO();
            BeanUtils.copyProperties(item, servicePracticeDTO);
            servicePracticeDTOS.add(servicePracticeDTO);
        });
        return new ResponseResult<List<ServicePracticeDTO>>(ResponseCode.OK.code(), ResponseCode.OK.message(), servicePracticeDTOS);
    }
}