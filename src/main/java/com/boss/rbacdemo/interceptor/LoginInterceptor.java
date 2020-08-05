package com.boss.rbacdemo.interceptor;

import com.boss.rbacdemo.component.EncryptComponent;
import com.boss.rbacdemo.component.MyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author :覃玉锦
 * @create :2020-08-03 21:43:00
 * 对所有请求进行拦截，如果没有登录则无法访问任何接口，如果登录成功会将常用属性至于request方便使用
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private EncryptComponent encryptComponent;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String auth = request.getHeader(MyToken.AUTHORIZATION);
        if(auth!=null){
            MyToken myToken = encryptComponent.decryptToken(auth);
            request.setAttribute("uid", myToken.getUid());
            request.setAttribute("role", myToken.getRole());
        }
        else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"未登录");
        }

        return true;
    }
}
