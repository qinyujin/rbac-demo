package com.boss.gateway;

import com.boss.component.component.MyToken;
import com.boss.component.component.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author :覃玉锦
 * @create :2020-08-16 10:09:00
 */
@SpringBootTest
class GatewayApplicationTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void test1() {
        String auth = (String) redisUtil.get(MyToken.AUTHORIZATION);
        System.out.println("auth:"+auth);
    }
}