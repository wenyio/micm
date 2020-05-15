package com.iscolt.micm.business.controller;

import com.baidu.aip.util.Base64Util;
import com.iscolt.micm.business.service.FaceLoginService;
import com.iscolt.micm.commons.dto.FaceLoginResult;
import com.iscolt.micm.commons.dto.QRCode;
import com.iscolt.micm.commons.model.dto.ResponseCode;
import com.iscolt.micm.commons.dto.ResponseResult;
import com.iscolt.micm.commons.utils.BaiduAiUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/4/2020
 * @see: com.iscolt.micm.business.controller
 * @version: v1.0.0
 */
@RestController
@RequestMapping("/user/faceLogin")
public class FaceLoginController {

    @Resource
    private FaceLoginService faceLoginService;

    @Resource
    private BaiduAiUtil baiduAiUtil;

    /**
     * 获取刷脸登录二维码
     *  返回: QRCode对象(code, image)
     */
    @GetMapping(value = "/qrcode")
    public ResponseResult<QRCode> qrcode() throws Exception {
        QRCode qrCode = faceLoginService.getQRCode();
        return new ResponseResult<QRCode>(ResponseCode.OK.code(),"成功获取二维码", qrCode);
    }

    /**
     * 检查二维码：登录页面轮询调用此方法，根据唯一标识code判断用户登录情况
     *   查询二维码扫描状态
     *      返回值: FaceLoginResult
     *          state: -1未, 0失败, 1成功 (userId和token)
     */
    @GetMapping(value = "/qrcode/{code}")
    public ResponseResult<FaceLoginResult> qrcodeCeck(@PathVariable(name = "code") String code) throws Exception {
        FaceLoginResult result = faceLoginService.checkQRCode(code);
        return new ResponseResult<FaceLoginResult>(ResponseCode.OK.code(),"操作成功", result);
    }

    /**
     * 人脸登录：根据落地页随机拍摄的面部头像进行登录
     *          根据拍摄的图片调用百度云AI进行检索查找
     */
    @PostMapping(value = "/{code}")
    public ResponseResult<Void> loginByFace(@PathVariable(name = "code") String code, @RequestParam(name = "file") MultipartFile attachment) throws Exception {
        // 获取用户信息标识(不为null 登陆成功)
        String userId = faceLoginService.loginByFace(code, attachment);

        // 登录成功
        if (userId != null) {
            return new ResponseResult<Void>(ResponseCode.OK.code());
        }

        // 登录失败
        else {
            return new ResponseResult<Void>(ResponseCode.FAIL.code());
        }

    }


    /**
     * 图像检测，判断图片中是否存在面部头像
     */
    @PostMapping(value = "/checkFace")
    public ResponseResult checkFace(@RequestParam(name = "file") MultipartFile attachment) throws Exception {
        String image = Base64Util.encode(attachment.getBytes());
        Boolean aBoolean = baiduAiUtil.faceCheck(image);

        // true 有面部信息
        if (aBoolean) {
            return new ResponseResult<>(ResponseCode.OK.code(),"检测到面部信息");
        }

        // false 没有
        else {
            return new ResponseResult<>(ResponseCode.FAIL.code(),"未检测到面部信息");
        }

    }

}
