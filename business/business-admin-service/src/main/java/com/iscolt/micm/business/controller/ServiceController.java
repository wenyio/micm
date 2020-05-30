package com.iscolt.micm.business.controller;

import com.iscolt.micm.commons.dto.ResponseResult;
import com.iscolt.micm.commons.model.dto.ResponseCode;
import com.iscolt.micm.provider.api.SysServiceService;
import com.iscolt.micm.provider.entity.SysService;
import org.apache.dubbo.config.annotation.Reference;
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

    @GetMapping(value = "")
    public ResponseResult<Page<SysService>> list(@PageableDefault(sort = {"created"}, direction = ASC) Pageable pageable) {
        Page<SysService> sysServices = sysServiceService.listAll(pageable);
        return new ResponseResult<Page<SysService>>(ResponseCode.OK.code(), ResponseCode.OK.message(), sysServices);
    }

    @PostMapping(value = "add")
    public ResponseResult<Page<SysService>> add(@RequestBody SysService sysService) {
        return new ResponseResult<Page<SysService>>(ResponseCode.FAIL.code(), ResponseCode.FAIL.message());
    }

    @PostMapping(value = "update")
    public ResponseResult<Page<SysService>> update() {
        return new ResponseResult<Page<SysService>>(ResponseCode.FAIL.code(), ResponseCode.FAIL.message());
    }

    @GetMapping(value = "delete/{id}")
    public ResponseResult<Page<SysService>> delete(@PathVariable(value = "id") Integer id) {
        SysService sysService = sysServiceService.removeById(id);

        if (sysService != null) {
            return new ResponseResult<Page<SysService>>(ResponseCode.OK.code(), ResponseCode.OK.message());
        }

        return new ResponseResult<Page<SysService>>(ResponseCode.FAIL.code(), ResponseCode.FAIL.message());
    }
}
