package com.boss.rbacdemo.interceptor;

import com.boss.rbacdemo.component.RequestComponent;
import com.boss.rbacdemo.dao.RoleDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author :覃玉锦
 * @create :2020-08-03 22:06:00
 * 权限拦截器，对部分需要管理员权限的接口进行拦截
 */
@Component
@Slf4j
public class UserInterceptor implements HandlerInterceptor {
    @Autowired
    private RequestComponent requestComponent;

    @Autowired
    private RoleDao roleDao;

    private static final String ADMIN = "Admin";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        int roleId = requestComponent.getRole();
        String detail = roleDao.getRoleById(roleId);
        log.debug("{}", detail);

        if (!detail.equals(ADMIN)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "无权限访问");
        }
        return true;
    }
}
