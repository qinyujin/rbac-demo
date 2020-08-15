package com.boss.rbac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author :覃玉锦
 * @create :2020-08-15 16:52:00
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.boss.component","com.boss.rbac"})
public class RbacApplication {
    public static void main(String[] args) {
        SpringApplication.run(RbacApplication.class, args);
    }
}
