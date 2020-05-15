package com.iscolt.micm.business.controller;

import com.google.common.collect.Maps;
import com.iscolt.micm.business.BusinessException;
import com.iscolt.micm.business.BusinessStatus;
import com.iscolt.micm.business.dto.LoginInfo;
import com.iscolt.micm.business.dto.LoginParam;
import com.iscolt.micm.business.feign.ProfileFeign;
import com.iscolt.micm.cloud.api.MessageService;
import com.iscolt.micm.cloud.dto.SysLogDTO;
import com.iscolt.micm.cloud.dto.UmsAdminLoginLogDTO;
import com.iscolt.micm.cloud.feign.MessageFeign;
import com.iscolt.micm.commons.model.dto.ResponseCode;
import com.iscolt.micm.commons.dto.ResponseResult;
import com.iscolt.micm.commons.utils.MapperUtils;
import com.iscolt.micm.commons.utils.OkHttpClientUtil;
import com.iscolt.micm.commons.utils.UserAgentUtils;
import com.iscolt.micm.provider.api.SysUserService;
import com.iscolt.micm.provider.api.UmsAdminService;
import com.iscolt.micm.provider.entity.SysUser;
import com.iscolt.micm.provider.entity.UmsAdmin;
import okhttp3.Response;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * 登录控制
 *
 * @Author: https://github.com/isColt
 * @Date: 3/29/2020
 * @Description: com.iscolt.micm.business.controller
 * @version: v1.0.0
 */
@RestController
public class LoginController {

    private static final String URL_OAUTH_TOKEN = "http://localhost:9001/oauth/token";

    @Value("${business.oauth2.grant_type}")
    public String oauth2_grant_type;

    @Value("${business.oauth2.client_id}")
    public String oauth2_client_id;

    @Value("${business.oauth2.client_secret}")
    public String oauth2_client_secret;

    @Resource(name = "userDetailsServiceBean")
    public UserDetailsService userDetailsService;

    @Resource
    public BCryptPasswordEncoder passwordEncoder;

    @Resource
    private MessageFeign messageFeign;

    @Resource
    public TokenStore tokenStore;

    @Resource
    private ProfileFeign profileFeign;

    @Reference(version = "1.0.0")
    private UmsAdminService umsAdminService;

    @Reference(version = "1.0.0")
    private SysUserService sysUserService;

    @Reference(version = "1.0.0")
    private MessageService messageService;

    @GetMapping(value = "/tenant")
    public String test() {
        return "你有权限?";
    }


    /**
     * 用户登录
     * @param loginParam
     * @return
     */
    @PostMapping(value = "/user/login")
    public ResponseResult<Map<String, Object>> login(@RequestBody LoginParam loginParam, HttpServletRequest request) {

        Map<String, Object> result = Maps.newHashMap();

        // 验证账号密码
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginParam.getUsername());
        if (userDetails == null || !passwordEncoder.matches(loginParam.getPassword(), userDetails.getPassword())){
            throw new BusinessException(BusinessStatus.ADMIN_PASSWORD);
//            return new ResponseResult<Map<String, Object>>(ResponseCode.FAIL.code(), "账号或密码错误", null);
        }

        Map<String, String> params = Maps.newHashMap();
        params.put("username", loginParam.getUsername());
        params.put("password", loginParam.getPassword());
        params.put("grant_type", oauth2_grant_type);
        params.put("client_id", oauth2_client_id);
        params.put("client_secret", oauth2_client_secret);

        try {
            Response response = OkHttpClientUtil.getInstance().postData(URL_OAUTH_TOKEN, params);
            String jsonString = Objects.requireNonNull(response.body()).string();
            Map<String, Object> jsonMap = MapperUtils.json2map(jsonString);
            String token = String.valueOf(jsonMap.get("access_token"));
            result.put("token", token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // TODO 发送管理员登陆日志
//        sendAdminLoginLog(userDetails.getUsername(), request);
        // TODO 更新用户的最近登录时间
        return new ResponseResult<Map<String, Object>>(ResponseCode.OK.code(), "登录成功", result);
    }

    /**
     * 获取登录信息
     *     用户名
     *     头像
     * @return
     */
    @GetMapping(value = "/user/info")
    public ResponseResult<LoginInfo> info() throws Exception {
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 获取个人信息
        String jsonString = profileFeign.info(authentication.getName());
        SysUser sysUser = MapperUtils.json2pojoByTree(jsonString, "data", SysUser.class);

        // 熔断后得结果返回到客户端
        if(sysUser == null) {
            return MapperUtils.json2pojo(jsonString, ResponseResult.class);
        }

        // 封装并返回结果
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setName(sysUser.getUsername()); // TODO 有需要改成 NickName
        loginInfo.setAvatar(sysUser.getAvatar());
        return new ResponseResult<LoginInfo>(ResponseCode.OK.code(), "获取用户信息", loginInfo);
    }

    /**
     * 注销
     * @return
     */
    @PostMapping(value = "/user/logout")
    public ResponseResult<Void> logout(HttpServletRequest request) {
        String token = request.getParameter("access_token");
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return new ResponseResult<Void>(ResponseCode.OK.code(), "用户注销", null);
    }



    /**
     * 发送登录日志
     * @param username
     * @param request
     * @return
     */
    private Boolean sendAdminLoginLog(String username, HttpServletRequest request) {
//        UmsAdmin umsAdmin = umsAdminService.get(username);
        SysUser sysUser = sysUserService.get(username);

        String ip = UserAgentUtils.getIpAddr(request);
        String address = UserAgentUtils.getIpInfo(ip).getCity();
        String userAgent = UserAgentUtils.getBrowser(request).getName();

        SysLogDTO dto = new SysLogDTO();
        dto.setUserId(sysUser.getId());
        dto.setCreateTime(new Date());
        dto.setIp(ip);
        dto.setAddress(address);
        dto.setUserAgent(userAgent);

        return messageService.sendSysLog(dto);
    }
}
