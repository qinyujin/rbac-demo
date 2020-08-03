package com.boss.rbacdemo.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author :覃玉锦
 * @create :2020-08-03 21:02:00
 * 提供token编码
 */
@Component
public class EncryptComponent {

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${my.secretkey}")
    private String secrytkey;

    @Value("${my.salt}")
    private String salt;

    @Autowired
    private TextEncryptor encryptor;

    @Bean
    public TextEncryptor getTextEncryptor() {
        return Encryptors.text(secrytkey, salt);
    }

    public String encryptToken(MyToken token) {
        try {
            String json = objectMapper.writeValueAsString(token);
            return encryptor.encrypt(json);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "服务器错误");
        }
    }

    public MyToken decryptToken(String auth) {
        String json = encryptor.decrypt(auth);
        try {
            return objectMapper.readValue(json, MyToken.class);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "无权限");
        }
    }
}
