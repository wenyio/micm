package com.iscolt.micm.business.controller;

import com.iscolt.micm.business.dto.param.PracticeOptionParam;
import com.iscolt.micm.commons.dto.FaceLoginResult;
import com.iscolt.micm.commons.dto.QRCode;
import com.iscolt.micm.commons.dto.ResponseResult;
import com.iscolt.micm.commons.model.dto.ResponseCode;
import com.iscolt.micm.commons.utils.IdWorker;
import com.iscolt.micm.commons.utils.QRCodeUtil;
import com.iscolt.micm.provider.api.ServicePracticeService;
import com.iscolt.micm.provider.api.ServiceUserPracticeService;
import com.iscolt.micm.provider.api.SysUserService;
import com.iscolt.micm.provider.entity.ServicePractice;
import com.iscolt.micm.provider.entity.SysUser;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 活动的一些操作
 * <p>
 * Description: 报名，取消报名，签到，签退，判断是否报名
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/13
 * @see: com.iscolt.micm.business.controller
 * @version: v1.0.0
 */
@RestController
@RequestMapping(value = "practice/option")
public class PracticeOptionController {

    @Value("${qr.qrUrl}")
    private String qrUrl; // 二维码地址

    @Resource
    private IdWorker idWorker;

    @Reference(version = "1.0.0")
    private SysUserService sysUserService;

    @Reference(version = "1.0.0")
    private ServicePracticeService servicePracticeService;

    @Reference(version = "1.0.0")
    private ServiceUserPracticeService serviceUserPracticeService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 活动报名
     * @return
     */
    @GetMapping(value = "apply/{practiceId}")
    public ResponseResult<Void> apply(@PathVariable(value = "practiceId") long practiceId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = sysUserService.get(authentication.getName());
        Boolean result = serviceUserPracticeService.apply(sysUser.getId(), sysUser.getTenantId(), practiceId);
        if (result) {
            return new ResponseResult<Void>(ResponseCode.OK.code(), ResponseCode.OK.message());
        }
        return new ResponseResult<Void>(ResponseCode.FAIL.code(), ResponseCode.FAIL.message());
    }

    /**
     * 取消报名
     * @return
     */
    @PostMapping(value = "notApply/{practiceId}")
    public ResponseResult<Void> notApply(@PathVariable(value = "practiceId") long practiceId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = sysUserService.get(authentication.getName());
        Boolean result = serviceUserPracticeService.notApply(sysUser.getId(), sysUser.getTenantId(), practiceId);
        if (result) {
            return new ResponseResult<Void>(ResponseCode.OK.code(), ResponseCode.OK.message());
        }
        return new ResponseResult<Void>(ResponseCode.FAIL.code(), ResponseCode.FAIL.message());
    }

    /**
     * 验证是否报名
     * @return
     */
    @PostMapping(value = "judge/{practiceId}")
    public ResponseResult<Void> judge(@PathVariable(value = "practiceId") long practiceId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = sysUserService.get(authentication.getName());
        Boolean result = serviceUserPracticeService.judge(sysUser.getId(), sysUser.getTenantId(), practiceId);
        if (result) {
            return new ResponseResult<Void>(ResponseCode.OK.code(), ResponseCode.OK.message());
        }
        return new ResponseResult<Void>(ResponseCode.FAIL.code(), ResponseCode.FAIL.message());
    }

    /**
     * 签到/签退（用户扫码）
     * @return
     */
    @GetMapping(value = "check/{key}")
    public ResponseResult<Void> check(@PathVariable(value = "key") String key) {
        // 从 redis里根据 key 获取活动ID
        Long practiceId = (Long) redisTemplate.opsForValue().get(key);
        if (practiceId == null) {
            return new ResponseResult<Void>(ResponseCode.FAIL.code(), "二维码已过期");
        }

        // 认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = sysUserService.get(authentication.getName());

        return new ResponseResult<Void>(ResponseCode.OK.code(), check(sysUser, practiceId));
    }

    /**
     * 签到/签退（用户扫码）
     * @return
     */
    @PostMapping(value = "check")
    public ResponseResult<Void> check(@RequestBody PracticeOptionParam practiceOptionParam) {
        SysUser user = sysUserService.getById(practiceOptionParam.getUserId());
        return new ResponseResult<Void>(ResponseCode.OK.code(), check(user, practiceOptionParam.getPracticeId()));
    }

    /**
     * 获取活动二维码， 用于签退签到
     *  //TODO 管理员获取
     *  返回: QRCode对象(code, image)
     */
    @GetMapping(value = "qrcode/{practiceId}")
    public ResponseResult<QRCode> checkInQrCode(@PathVariable(value = "practiceId") long practiceId) throws Exception {
        QRCode qrCode = getQRCode(practiceId);
        return new ResponseResult<QRCode>(ResponseCode.OK.code(),"成功获取二维码", qrCode);
    }

    /**
     * 判断是签到还是签退
     * @param user
     * @param practiceId
     * @return
     */
    public String check(SysUser user, long practiceId) {
        ServicePractice servicePractice = servicePracticeService.getById(practiceId);
        long begin = servicePractice.getBegin().getTime();
        long end = servicePractice.getEnd().getTime();
        Timestamp nowDate = new Timestamp(new Date().getTime());
        long now = nowDate.getTime();

        // 活动开始前后5分钟
        if ((begin-300000) <= now && now <= (begin+300000)) {
            return serviceUserPracticeService.checkIn(user.getId(), user.getTenantId(), servicePractice.getId());
        }

        // 活动结束前后5分钟
        else if((end-300000) <= now && now <= (end+300000)) {
            return serviceUserPracticeService.checkOut(user.getId(), user.getTenantId(), servicePractice.getId());
        }

        else {
            return "活动未开始或已经结束";
        }
    }

    /**
     * 创建二维码
     * @return
     * @throws Exception
     */
    public QRCode getQRCode(long practiceId) throws Exception {

        // 1. 创建唯一标识
        String code = idWorker.nextId() + "";

        // 2. 生成二维码放入key (key：code，value：practiceId)
        String content = getCacheKey(code);
        String file = QRCodeUtil.crateQRCode(content);

        // 3. 存入活动id，设置过期时间，过期后将无法获得活动id，无法签到
        redisTemplate.boundValueOps(getCacheKey(code)).set(practiceId, 5, TimeUnit.MINUTES); // 状态对象, 失效时间, 单位

        return new QRCode(code, file);
    }

    /**
     * 构造缓存key
     *
     * @param code
     * @return
     */
    private String getCacheKey(String code) {
        return "checkCode_" + code;
    }
}
