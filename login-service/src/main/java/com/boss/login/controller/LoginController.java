package com.boss.login.controller;

import com.boss.component.component.EncryptComponent;
import com.boss.component.component.MyToken;
import com.boss.component.component.RedisUtil;
import com.boss.mvc.entity.CommonResult;
import com.boss.mvc.entity.User;
import com.boss.mvc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;

/**
 * @author :覃玉锦
 * @create :2020-08-03 21:12:00
 * 登录,校验用户名密码后将用户id,角色信息封装成token存入header里
 */
@CrossOrigin
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

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("login")
    public CommonResult login(HttpServletResponse response, @RequestBody User user) {

        User u = userService.getUserByName(user.getName());
        //用户名和密码匹配成功
        if (u != null && encoder.matches(user.getPassword(), u.getPassword())) {
            //必须保证用户有对应角色,不然抛异常
            Integer role = userService.getRole(u.getId());
            if (role == null) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "该用户还没有角色，无法访问");
            MyToken token = new MyToken(u.getId(), role);
            String auth = encryptComponent.encryptToken(token);
            //将编码后的token信息auth存入redis
            redisUtil.set(MyToken.AUTHORIZATION, auth);
            log.debug("用户角色 id：{}", role);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名密码错误");
        }
        return new CommonResult(200, "登陆成功", user);
    }
}
