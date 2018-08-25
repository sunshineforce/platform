package com.platform.interceptor;

import com.platform.constants.CommonConstant;
import com.platform.entity.dto.AuthVo;
import com.platform.util.MaterialBindUtils;
import com.platform.utils.ShiroUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/8/25 17:25
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */


public class AuthTokenInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AuthVo loginToken = MaterialBindUtils.getLoginToken();
        while (true){
            if (MaterialBindUtils.isExpire(loginToken)) {
                loginToken = MaterialBindUtils.getLoginToken();
                Subject subject = ShiroUtils.getSubject();
                subject.getSession().setAttribute(CommonConstant.AUTH_TOKEN,loginToken);
            }
        }
    }
}
