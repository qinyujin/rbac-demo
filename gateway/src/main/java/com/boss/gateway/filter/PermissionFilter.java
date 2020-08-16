package com.boss.gateway.filter;

import com.boss.component.component.EncryptComponent;
import com.boss.component.component.MyToken;
import com.boss.component.component.RedisUtil;
import com.boss.mvc.dao.MenuDao;
import com.boss.mvc.dao.PermissionDao;
import com.boss.mvc.dao.RoleDao;
import com.boss.mvc.entity.Permission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author :覃玉锦
 * @create :2020-08-16 10:05:00
 * 做的修改：引入mvc模块
 */
@Component
@Slf4j
public class PermissionFilter implements GlobalFilter, Ordered {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private EncryptComponent encryptComponent;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private PermissionDao permissionDao;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("进入角色请求拦截器");

        RequestPath pa = exchange.getRequest().getPath();
        String path = pa.toString();

        //给登录api放行
        if(path.equals("/api/login")){
            return chain.filter(exchange);
        }

        Set<Integer> pids = new HashSet<>();
        pids.clear();
        //
//        int roleId = requestComponent.getRole();
        String auth = (String) redisUtil.get(MyToken.AUTHORIZATION);
        MyToken myToken = encryptComponent.decryptToken(auth);
        int roleId=myToken.getRole();
        //
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

        //
//        StringBuffer requestURL = request.getRequestURL();
        String req = exchange.getRequest().getURI().toString();

//        String req = requestURL.toString();
        //
        log.debug("请求的url:{}", req);

//      检验请求发出的url是否在权限集url中,
        for (String pName : pNames) {
            if (req.contains(pName)) {
                return chain.filter(exchange);
            }
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "无权限");
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
