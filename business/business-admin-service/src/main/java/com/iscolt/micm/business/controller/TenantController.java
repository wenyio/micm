package com.iscolt.micm.business.controller;

import com.iscolt.micm.business.dto.params.ServiceParam;
import com.iscolt.micm.business.dto.params.TenantParam;
import com.iscolt.micm.commons.dto.ResponseResult;
import com.iscolt.micm.commons.model.dto.ResponseCode;
import com.iscolt.micm.provider.api.SysTenantService;
import com.iscolt.micm.provider.entity.SysService;
import com.iscolt.micm.provider.entity.SysTenant;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * 获取租户列表
     * @param pageable
     * @return
     */
    @GetMapping(value = "")
    public ResponseResult<Page<SysTenant>> list(@PageableDefault(sort = {"created"}, direction = ASC) Pageable pageable) {
        Page<SysTenant> sysTenants = sysTenantService.listAll(pageable);
        return new ResponseResult<Page<SysTenant>>(ResponseCode.OK.code(), ResponseCode.OK.message(), sysTenants);
    }

    /**
     * 添加或更新租户
     *  TODO 需要检测用户是否已经关联其他租户，一个用户只能关联一个租户
     * @param tenantParam
     * @return
     */
    @PostMapping(value = "save")
    public ResponseResult<Void> save(@RequestBody TenantParam tenantParam) {
        SysTenant sysTenant = new SysTenant();
        BeanUtils.copyProperties(tenantParam, sysTenant);
        // 如果id为空则创建对象
        if ("null".equals(String.valueOf(tenantParam.getId()))) {
            SysTenant result = sysTenantService.create(sysTenant);
        }
        // 否在更新对象
        else {
            SysTenant update = sysTenantService.update(sysTenant);
        }
        return new ResponseResult<Void>(ResponseCode.OK.code(), ResponseCode.OK.message());
    }

    /**
     * 根据id改变状态
     *  TODO 现在使用的是更新方法， 需要专门写一个方法来改变状态
     * @param id
     * @return
     */
    @GetMapping(value = "status/{id}")
    public ResponseResult<Void> changeStatus(@PathVariable(value = "id") Integer id) {
        SysTenant tenant = sysTenantService.getById(id);
        tenant.setStatus(!tenant.getStatus());
        SysTenant update = sysTenantService.update(tenant);
        return new ResponseResult<Void>(ResponseCode.OK.code(), ResponseCode.OK.message());
    }

    /**
     * 根据id删除服务
     *  TODO 删除服务前需要读取那些租户还在使用此服务，级联删除
     * @param id
     * @return
     */
    @GetMapping(value = "delete/{id}")
    public ResponseResult<Page<SysService>> delete(@PathVariable(value = "id") Integer id) {
        SysTenant sysTenant = sysTenantService.removeById(id);

        if (sysTenant != null) {
            return new ResponseResult<Page<SysService>>(ResponseCode.OK.code(), ResponseCode.OK.message());
        }

        return new ResponseResult<Page<SysService>>(ResponseCode.FAIL.code(), ResponseCode.FAIL.message());
    }
}
