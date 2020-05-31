package com.iscolt.micm.business.controller;

import com.iscolt.micm.business.dto.params.ServiceParam;
import com.iscolt.micm.commons.dto.ResponseResult;
import com.iscolt.micm.commons.model.dto.ResponseCode;
import com.iscolt.micm.provider.api.SysServiceService;
import com.iscolt.micm.provider.entity.SysService;
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

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;

/**
 * 系统服务管理
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
@RequestMapping(value = "admin/services")
public class ServiceController {

    @Reference(version = "1.0.0")
    private SysServiceService sysServiceService;

    /**
     * 服务列表
     * @param pageable
     * @return
     */
    @GetMapping(value = "")
    public ResponseResult<Page<SysService>> list(@PageableDefault(sort = {"created"}, direction = ASC) Pageable pageable) {
        Page<SysService> sysServices = sysServiceService.listAll(pageable);
        return new ResponseResult<Page<SysService>>(ResponseCode.OK.code(), ResponseCode.OK.message(), sysServices);
    }

    /**
     * 添加或更新服务
     * @param serviceParam
     * @return
     */
    @PostMapping(value = "save")
    public ResponseResult<Void> save(@RequestBody ServiceParam serviceParam) {
        SysService sysService = new SysService();
        BeanUtils.copyProperties(serviceParam, sysService);
        // 如果id为空则创建对象
        if ("null".equals(String.valueOf(serviceParam.getId()))) {
            SysService result = sysServiceService.create(sysService);
        }
        // 否在更新对象
        else {
            SysService update = sysServiceService.update(sysService);
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
        SysService service = sysServiceService.getById(id);
        service.setStatus(!service.getStatus());
        SysService update = sysServiceService.update(service);
        return new ResponseResult<Void>(ResponseCode.OK.code(), ResponseCode.OK.message());
    }

    /**
     * 根据id删除服务
     *  TODO 删除服务前需要读取那些租户还在使用此服务，级联删除
     * @param id
     * @return
     */
    @GetMapping(value = "delete/{id}")
    public ResponseResult<Void> delete(@PathVariable(value = "id") Integer id) {
        SysService sysService = sysServiceService.removeById(id);

        if (sysService != null) {
            return new ResponseResult<Void>(ResponseCode.OK.code(), ResponseCode.OK.message());
        }

        return new ResponseResult<Void>(ResponseCode.FAIL.code(), ResponseCode.FAIL.message());
    }
}
