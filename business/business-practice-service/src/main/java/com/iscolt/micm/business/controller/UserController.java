package com.iscolt.micm.business.controller;

import com.iscolt.micm.business.BusinessException;
import com.iscolt.micm.business.BusinessStatus;
import com.iscolt.micm.business.dto.SysUserDTO;
import com.iscolt.micm.commons.dto.QRCode;
import com.iscolt.micm.commons.dto.ResponseResult;
import com.iscolt.micm.commons.model.dto.ResponseCode;
import com.iscolt.micm.commons.provider.view.UserPracticeView;
import com.iscolt.micm.commons.utils.IdWorker;
import com.iscolt.micm.commons.utils.QRCodeUtil;
import com.iscolt.micm.provider.api.ServiceUserPracticeService;
import com.iscolt.micm.provider.api.SysUserService;
import com.iscolt.micm.provider.api.TenantUserInfoService;
import com.iscolt.micm.provider.entity.SysUser;
import com.iscolt.micm.provider.entity.TenantUserInfo;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/11
 * @see: com.iscolt.micm.business.controller
 * @version: v1.0.0
 */
@RestController
@RequestMapping(value = "practice/user")
public class UserController {

    @Reference(version = "1.0.0")
    private SysUserService sysUserService;

    @Resource
    private IdWorker idWorker;

    @Reference(version = "1.0.0")
    private TenantUserInfoService tenantUserInfoService;

    @Reference(version = "1.0.0")
    private ServiceUserPracticeService serviceUserPracticeService;

    /**
     * 获取用户详情信息
     * @return
     */
    @GetMapping(value = "detail/info")
    public ResponseResult<TenantUserInfo> detailInfo() {
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = sysUserService.get(authentication.getName());
        TenantUserInfo info = tenantUserInfoService.getByUserId(sysUser.getId());
        return new ResponseResult<TenantUserInfo>(ResponseCode.OK.code(), ResponseCode.OK.message(), info);
    }

    /**
     * 获取用户报名的活动
     * @return
     */
    @GetMapping(value = "getPractice")
    public ResponseResult<List<UserPracticeView>> getPractice() {
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = sysUserService.get(authentication.getName());
        List<UserPracticeView> userPracticeViews = new ArrayList<>();
        try {
            userPracticeViews = serviceUserPracticeService.selectByUserIdAndTenantId(sysUser.getId(), sysUser.getTenantId());
        } catch (Exception e) {
            throw new BusinessException(BusinessStatus.UNKNOWN);
        }
        return new ResponseResult<List<UserPracticeView>>(ResponseCode.OK.code(), ResponseCode.OK.message(), userPracticeViews);
    }

    /**
     * 获取用户报名的活动次数
     * @return
     */
    @GetMapping(value = "getPracticeNum")
    public ResponseResult<Integer> getPracticeNum() {
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = sysUserService.get(authentication.getName());
        int num = 0;
        try {
            num = serviceUserPracticeService.countUserPracticeNum(sysUser.getId(), sysUser.getTenantId());
        } catch (Exception e) {
            throw new BusinessException(BusinessStatus.UNKNOWN);
        }
        return new ResponseResult<Integer>(ResponseCode.OK.code(), ResponseCode.OK.message(), num);
    }

    /**
     * 获取用户的活动积分
     * @return
     */
    @GetMapping(value = "getPracticeIntegral")
    public ResponseResult<Integer> getPracticeIntegral() {
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = sysUserService.get(authentication.getName());
        int integral = 0;
        try {
            integral = serviceUserPracticeService.sumUserPracticeIntegral(sysUser.getId(), sysUser.getTenantId());
        } catch (Exception e) {
            throw new BusinessException(BusinessStatus.UNKNOWN);
        }
        return new ResponseResult<Integer>(ResponseCode.OK.code(), ResponseCode.OK.message(), integral);
    }


    /**
     * 获取用户二维码
     *  //TODO 管理员获取
     *  返回: QRCode对象(code, image)
     */
    @GetMapping(value = "qrcode")
    public ResponseResult<QRCode> checkInQrCode() throws Exception {
        // 认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = sysUserService.get(authentication.getName());
        QRCode qrCode = getQRCode(sysUser.getId());
        return new ResponseResult<QRCode>(ResponseCode.OK.code(),"成功获取二维码", qrCode);
    }

    /**
     * 创建二维码
     * @return
     * @throws Exception
     */
    public QRCode getQRCode(int userId) throws Exception {
        String file = QRCodeUtil.crateQRCode(userId+"");
        return new QRCode(idWorker.nextId()+"", file);
    }
}
