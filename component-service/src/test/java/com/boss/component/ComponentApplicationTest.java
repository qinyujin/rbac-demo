package com.boss.component;

import com.boss.component.component.EncryptComponent;
import com.boss.component.component.MyToken;
import com.boss.mvc.dao.MenuDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author :覃玉锦
 * @create :2020-08-15 15:19:00
 */
@SpringBootTest
class ComponentApplicationTest {

    @Value("${my.salt}")
    private String value;

    @Value("${my.secretkey}")
    private String secret;

    @Autowired
    private EncryptComponent encryptComponent;

    @Autowired
    private MenuDao menuDao;

    @Test
    void test1(){
        MyToken myToken = new MyToken(2, 1);
        String auth = encryptComponent.encryptToken(myToken);
        System.out.println(auth);
    }

    @Test
    void test2(){

    }
}