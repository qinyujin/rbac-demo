package com.boss.rbacdemo.contoller;

import com.boss.rbacdemo.component.RequestComponent;
import com.boss.rbacdemo.entity.User;
import com.boss.rbacdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author :覃玉锦
 * @create :2020-08-03 21:33:00
 * 用户端口，实现权限区分，部分接口只有权限通过才能访问
 * 访问控制:根据权限判断用户是否具有访问api的权限
 * 页面元素:通过返回的url来获取用户相应页面
 */
@RestController
@RequestMapping("/api/user/")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RequestComponent requestComponent;

    @PostMapping("saveUser")
    private Map saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return Map.of("user", user);
    }

    @GetMapping("getUser")
    private Map getUserById(){
        int uid = requestComponent.getUid();
        User user = userService.getUserById(uid);
        return Map.of("user",user);
    }


}
