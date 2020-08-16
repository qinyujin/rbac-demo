package com.boss.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author :覃玉锦
 * @create :2020-08-15 15:02:00
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(value = {"com.boss.component","com.boss.gateway","com.boss.mvc"})
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
