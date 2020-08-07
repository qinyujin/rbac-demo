package com.boss.rbacdemo.interceptor;

import com.boss.rbacdemo.component.RequestComponent;
import com.boss.rbacdemo.dao.MenuDao;
import com.boss.rbacdemo.dao.PermissionDao;
import com.boss.rbacdemo.dao.RoleDao;
import com.boss.rbacdemo.entity.Permission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.debug("进入 角色请求拦截器");

        Set<Integer> pids = new HashSet<>();
        pids.clear();
        int roleId = requestComponent.getRole();

        List<Integer> menusId = roleDao.getMenus(roleId);
        menusId.forEach(m -> {
            List<Integer> permissiones = menuDao.getPermissiones(m);
            pids.addAll(permissiones);
        });

//        pnames:权限名称集,可以通过这个来判断请求是否再当前权限集中
        List<String> pNames = new ArrayList<>();
        pNames.clear();
        pids.forEach(p -> {
            Permission permission = permissionDao.selectById(p);
            pNames.add(permission.getUrl());
        });
        log.debug("权限集:{}", pNames);
        StringBuffer requestURL = request.getRequestURL();
        String req = requestURL.toString();
        log.debug("请求的url:{}", requestURL);

//      检验请求发出的url是否在权限集url中,
        for (String pName : pNames) {
            if (req.contains(pName)) {
                return true;
            }
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "无权限");
    }
}
