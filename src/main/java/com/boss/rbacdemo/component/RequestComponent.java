package com.boss.rbacdemo.component;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * 提供从request中取id或者role
 */
@Component
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