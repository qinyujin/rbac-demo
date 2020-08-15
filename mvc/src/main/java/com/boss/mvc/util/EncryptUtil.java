package com.boss.mvc.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author :覃玉锦
 * @create :2020-08-15 22:32:00
 */
@Component
public class EncryptUtil {
    @Autowired
    private ObjectMapper objectMapper;

    @Value("${my.secretkey}")
    private String secrytkey;

    @Value("${my.salt}")
    private String salt;

    /*@Resource(name = "getMvcTextEncryptor")
    private TextEncryptor encryptor;*/

    /*@Bean
    @Lazy
    public TextEncryptor getMvcTextEncryptor() {
        return Encryptors.text(secrytkey, salt);
    }*/

    public String encryptToken(MvcMyToken token) {
        //
        TextEncryptor encryptor =  Encryptors.text(secrytkey,salt);
        //
        try {
            String json = objectMapper.writeValueAsString(token);
            return encryptor.encrypt(json);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "服务器错误");
        }
    }

    public MvcMyToken decryptToken(String auth) {
        //
        TextEncryptor encryptor =  Encryptors.text(secrytkey,salt);
        //
        String json = encryptor.decrypt(auth);
        try {
            return objectMapper.readValue(json, MvcMyToken.class);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "无权限");
        }
    }
}
