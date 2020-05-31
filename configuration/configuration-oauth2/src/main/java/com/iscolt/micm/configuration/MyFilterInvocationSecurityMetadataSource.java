package com.iscolt.micm.configuration;

import com.iscolt.micm.commons.provider.view.SysRoleApiView;
import com.iscolt.micm.provider.api.SysPermissionApiService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/7/2020
 * @see: com.iscolt.micm.business.configure
 * @version: v1.0.0
 */
@Component
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Reference(version = "1.0.0")
    private SysPermissionApiService sysPermissionApiService;

    private AntPathMatcher antPathMatcher = new AntPathMatcher(); // 模糊匹配 如何 auth/**   auth/auth

//    private HashMap<String,String> urlRoleMap = null; // url + 权限

    private List<SysRoleApiView> sysRoleApiViews = null; // 所有URL及对应的角色

    @PostConstruct
    public void init() {
        loadResourceDefine();
    }

    /**
     * 加载资源
     */
    public void loadResourceDefine(){
        try {
            sysRoleApiViews = sysPermissionApiService.selectAllRoleAndApi();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("定位:::::::::::::> sysPermissionApiService.selectAllRoleAndApi()");
        }
        // TODO 记录一次瞎使用hashmap的后果...
//        urlRoleMap = new HashMap<String,String>(){{
//            try {
//                sysRoleApiViews = sysPermissionApiService.selectAllRoleAndApi();
//                sysRoleApiViews.forEach(sysRoleApiView -> {
//                    System.out.println(sysRoleApiView.toString());
//                    put(sysRoleApiView.getUrl(), sysRoleApiView.getEnname());
//                });
//                System.out.println(urlRoleMap);
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.out.println("定位:::::::::::::> sysPermissionApiService.selectAllRoleAndApi()");
//            }
//        }};
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 每次请求都需要重新加载, 以防权限更新
        loadResourceDefine();

        String requestUrl = ((FilterInvocation) object).getRequest().getMethod() + ((FilterInvocation) object).getRequest().getRequestURI();
        List<String> roles = new ArrayList<>();
        sysRoleApiViews.forEach(item -> {
            if (antPathMatcher.match(item.getUrl(), requestUrl)) {
                roles.add(item.getEnname());
            }
        });
//        for (Map.Entry<String, String> entry : urlRoleMap.entrySet()) {
//            if (antPathMatcher.match(entry.getKey(), requestUrl)){ // 如果请求得URL存在, 就添加权限
//                roles.add(entry.getValue());
//            }
//        }
        String[] newArr = roles.toArray(new String[roles.size()]);
        return SecurityConfig.createList(newArr);
//        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}