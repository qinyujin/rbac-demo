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

/**
 * @author :覃玉锦
 * @create :2020-08-03 21:12:00
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
    public boolean login(HttpServletResponse response, @RequestBody User user) {
        int num = user.getNum();
        User u = userService.getUserByNum(num);
        //如果找到用户,校验密码
        if (u != null && encoder.matches(user.getPassword(), u.getPassword())) {
            MyToken myToken = new MyToken(u.getNum(), u.getRole());
            //把所需用户信息（num、role）放入header，减少访问数据库次数
            response.setHeader(MyToken.AUTHORIZATION, encryptComponent.encryptToken(myToken));
            return true;

        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名或者密码错误");
    }
}
