package com.iscolt.micm.business.controller;

import com.iscolt.micm.business.dto.params.TenantServiceParam;
import com.iscolt.micm.commons.dto.ResponseResult;
import com.iscolt.micm.commons.model.dto.ResponseCode;
import com.iscolt.micm.provider.api.SysServiceService;
import com.iscolt.micm.provider.api.SysTenantServiceService;
import com.iscolt.micm.provider.entity.SysService;
import com.iscolt.micm.provider.entity.SysTenant;
import com.iscolt.micm.provider.entity.SysTenantService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 租户订阅的服务
 *  TODO 需要添加金额的流水或日志
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
    private com.iscolt.micm.provider.api.SysTenantService tenantService;

    @Reference(version = "1.0.0")
    private SysServiceService sysServiceService;

    @Reference(version = "1.0.0")
    private SysTenantServiceService sysTenantServiceService;

    /**
     * 获取租户所订阅的服务
     * @param tenantServiceParam
     * @return
     */
    @PostMapping(value = "")
    public ResponseResult<List<SysTenantService>> getTenantServices(@RequestBody TenantServiceParam tenantServiceParam) {
        List<SysTenantService> sysTenantServices = sysTenantServiceService.selectByTenantId(tenantServiceParam.getTenantId());
        return new ResponseResult<List<SysTenantService>>(ResponseCode.OK.code(), ResponseCode.OK.message(), sysTenantServices);
    }

    /**
     * 根据id改变状态
     *  如果status为true，执行冻结操作，否则执行解冻操作（清空冻结时间）
     *  列如： 10：00冻结，12：00到期，11点解冻
     *  实际到期时间 = 到期时间 + （解冻时间 - 冻结时间）
     *  实际到期时间 = 12：00 + （11：00 - 10：00）= 13:00
     * @param id
     * @return
     */
    @GetMapping(value = "status/{id}")
    public ResponseResult<Void> changeStatus(@PathVariable(value = "id") Integer id) {
        SysTenantService sysTenantService = sysTenantServiceService.getById(id);
        if (sysTenantService != null) {
            if(sysTenantService.isStatus()) {
                // 冻结操作
                sysTenantService.setFreezeDate(new Timestamp(new Date().getTime()));
            } else {
                // 解冻操作
                // 取冻结的时长 当前日期 - 冻结日期
                long freezeTime = new Timestamp(new Date().getTime()).getTime() - sysTenantService.getFreezeDate().getTime();
                // 清空冻结时间 1970-01-01 08:00:00.0
                sysTenantService.setFreezeDate(new Timestamp(0));
                // 到期时间 = 到期时间 + 冻结时长
                sysTenantService.setExpirationDate(new Timestamp(sysTenantService.getExpirationDate().getTime() + freezeTime));
            }
            sysTenantService.setStatus(!sysTenantService.isStatus());
            SysTenantService update = sysTenantServiceService.update(sysTenantService);
            return new ResponseResult<Void>(ResponseCode.OK.code(), ResponseCode.OK.message());
        }
        return new ResponseResult<Void>(ResponseCode.FAIL.code(), ResponseCode.FAIL.message());
    }

    /**
     * 根据id删除服务
     * @param id
     * @return
     */
    @PostMapping(value = "delete/{id}")
    public ResponseResult<Void> delete(@RequestBody TenantServiceParam tenantServiceParam, @PathVariable(value = "id") Integer id) {
        SysTenant sysTenant = tenantService.getById(tenantServiceParam.getTenantId());
        SysService sysService = sysServiceService.getById(tenantServiceParam.getServiceId());
        // （到期时间 - 当前时间）* 价格
        SysTenantService sysTenantService = sysTenantServiceService.getById(id);
        long times = sysTenantService.getExpirationDate().getTime() - new Timestamp(new Date().getTime()).getTime();
        int days = (int) (times / 24 / 3600 / 1000); // 转换为天数
        // 已有金额 + 退还金额
        sysTenant.setBalance(sysTenant.getBalance().add(sysService.getPrice().multiply(BigDecimal.valueOf(days))));
        tenantService.update(sysTenant);
        // 取消订阅
        SysTenantService result = sysTenantServiceService.removeById(id);

        if (result != null) {
            return new ResponseResult<Void>(ResponseCode.OK.code(), ResponseCode.OK.message());
        }

        return new ResponseResult<Void>(ResponseCode.FAIL.code(), ResponseCode.FAIL.message());
    }

    /**
     * 续费
     *   传入天数，扣费 天*价格
     * @param tenantServiceParam
     * @return
     */
    @PostMapping(value = "renewal/{days}")
    public ResponseResult<Void> renewal(@RequestBody TenantServiceParam tenantServiceParam,
                                       @PathVariable(value = "days") Integer days) {
        SysTenantService sysTenantService = sysTenantServiceService.selectByServiceIdAndTenantId(tenantServiceParam.getServiceId(), tenantServiceParam.getTenantId());
        SysTenant sysTenant = tenantService.getById(tenantServiceParam.getTenantId());
        SysService sysService = sysServiceService.getById(tenantServiceParam.getServiceId());
        String result = "";
        // 已经订阅
        if (sysTenantService != null) {
            result = renewal(sysTenant, sysService, sysTenantService, days);
            sysTenantServiceService.update(sysTenantService);
            return new ResponseResult<Void>(ResponseCode.OK.code(), result);
        }
        // 未订阅
        else {
            sysTenantService = new SysTenantService();
            sysTenantService.setServiceId(tenantServiceParam.getServiceId());
            sysTenantService.setTenantId(tenantServiceParam.getTenantId());
            sysTenantService.setExpirationDate(new Timestamp(new Date().getTime())); // 设置到期时间为今天
            sysTenantService.setFreezeDate(new Timestamp(0)); // 初始化冻结时间
            result = renewal(sysTenant, sysService, sysTenantService, days);
            sysTenantServiceService.create(sysTenantService);
            return new ResponseResult<Void>(ResponseCode.OK.code(), result);
        }
    }

    /**
     * 订阅操作
     * @return
     */
    public String renewal(SysTenant sysTenant, SysService sysService, SysTenantService sysTenantService, Integer days) {

        // price1 大于price2返回1，price1 等于price2返回0，price1 小于price2返回-1
        // int a = price1.compareTo(price2);
        int result = sysTenant.getBalance().compareTo(sysService.getPrice().multiply(BigDecimal.valueOf(days)));
        // 余额大于消费金额
        if (result >= 0) {
            // 续费：扣除余额， 更新续费时间及到期时间
            // 1. 扣费
            sysTenant.setBalance(sysTenant.getBalance().subtract(sysService.getPrice().multiply(BigDecimal.valueOf(days))));
            tenantService.update(sysTenant);
            // 2. 续费操作
            sysTenantService.setRenewalDate(new Timestamp(new Date().getTime()));
            sysTenantService.setExpirationDate(new Timestamp(sysTenantService.getExpirationDate().getTime() + (long)1000*3600*24*days));
//            sysTenantServiceService.update(sysTenantService);
            return ResponseCode.OK.message();
        }
        return "操作失败！余额不足！";
    }
}
