package com.boss.component;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author :覃玉锦
 * @create :2020-08-15 10:14:00
 */
@SpringBootApplication
//需要加上com.boss.component包不然扫描不到encryptComponent？不知道什么原因
@ComponentScan(basePackages = {"com.boss.mvc","com.boss.component"})
public class ComponentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComponentApplication.class, args);
    }
}
