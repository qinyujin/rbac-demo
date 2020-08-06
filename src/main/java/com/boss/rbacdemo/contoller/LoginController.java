package com.boss.rbacdemo.contoller;

import com.boss.rbacdemo.component.EncryptComponent;
import com.boss.rbacdemo.component.MyToken;
import com.boss.rbacdemo.entity.User;
import com.boss.rbacdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author :覃玉锦
 * @create :2020-08-03 21:12:00
 * 登录,校验用户名密码后将用户id,角色信息封装成token存入header里
 */
@RestController
@RequestMapping("/api/")
@Slf4j
public class LoginController {
    @Autowired
    private EncryptComponent encryptComponent;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public Map login(HttpServletResponse response, @RequestBody User user) {
        log.debug("json User：{}", user);
        User u = userService.getUserByName(user.getName());
        log.debug("用户：{}", u);
        log.debug("123456密码校验情况：{}", encoder.matches("123456", u.getPassword()));
        log.debug("user传来的密码校验情况：{}", encoder.matches(user.getPassword(), u.getPassword()));
//        用户名和密码匹配成功
        if (u != null && encoder.matches(user.getPassword(), u.getPassword())) {
//        必须保证用户有对应角色,不然抛异常
            int role = userService.getRole(u.getId());
            MyToken token = new MyToken(u.getId(), role);
            String auth = encryptComponent.encryptToken(token);
            response.setHeader(MyToken.AUTHORIZATION, auth);
            log.debug("用户角色id：{}", role);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名密码错误");
        }
        return Map.of("user", u);
    }
}
