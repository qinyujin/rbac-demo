package com.boss.gateway.filter;

import com.boss.component.component.EncryptComponent;
import com.boss.component.component.MyToken;
import com.boss.component.component.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author :覃玉锦
 * @create :2020-08-15 20:23:00
 */
@Component
@Slf4j
public class LoginFilter implements GlobalFilter, Ordered {
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private EncryptComponent encryptComponent;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info("进入拦截器");
        RequestPath p = exchange.getRequest().getPath();
        String path = p.toString();

        //给登录api放行
        if(path.equals("/api/login")){
            return chain.filter(exchange);
        }

        //其他api判断登录状态
        //1、如果已经登录，则将用户id、角色id放入request中便于使用,再
        Boolean exists = redisUtil.exists(MyToken.AUTHORIZATION);
        System.out.println(exists);
        if(exists){
            return chain.filter(exchange);
        }

        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
