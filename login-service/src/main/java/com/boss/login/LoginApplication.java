package com.boss.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author :覃玉锦
 * @create :2020-08-15 10:21:00
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.boss.component","com.boss.login"})
public class LoginApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class, args);
    }
}
