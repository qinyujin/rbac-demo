package com.boss.rbacdemo.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author 覃玉锦
 * 提供从request中取id或者role
 */
@Component
@Slf4j
public class RequestComponent {
    public int getUid() {
        return (int) RequestContextHolder.currentRequestAttributes()
                .getAttribute("uid", RequestAttributes.SCOPE_REQUEST);
    }

    public int getRole() {
        return (int) RequestContextHolder.currentRequestAttributes()
                .getAttribute("role", RequestAttributes.SCOPE_REQUEST);
    }

}