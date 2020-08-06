package com.boss.rbacdemo;

import com.boss.rbacdemo.interceptor.LoginInterceptor;
import com.boss.rbacdemo.interceptor.UserInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author :覃玉锦
 * @create :2020-08-03 21:46:00
 * 配置拦截器的拦截路径
 */
@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录拦截器
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login");

        //用户权限拦截器
        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/api/user/**")
                .addPathPatterns("/api/menu/**")
                .addPathPatterns("/api/role/**")
                .addPathPatterns("/api/permission/**");
    }
}
