package com.boss.rbacdemo.contoller;

import com.boss.rbacdemo.component.RequestComponent;
import com.boss.rbacdemo.entity.User;
import com.boss.rbacdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author :覃玉锦
 * @create :2020-08-03 21:33:00
 * 用户端口，实现权限区分，部分接口只有权限通过才能访问
 */
@RestController
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RequestComponent requestComponent;

    @GetMapping("info")
    public Map querySelfInfo() {
        User user = userService.querySelfInfo();
        return Map.of("Personal Info", user);
    }

    @PatchMapping("updateName")
    public Map updateName(@RequestBody User user) {

        userService.updateName(requestComponent.getUid(), user.getName());
        return Map.of("newName:", user.getName());
    }

    /**
     * 管理员级别
     *
     * @param user
     * @return
     */
    @PostMapping("saveUser")
    public Map saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return Map.of("saved user", user);
    }

    /**
     * 管理员级别
     *
     * @param uid
     */
    @GetMapping("deleteUser/{uid}")
    public void deleteUser(@PathVariable int uid) {
        userService.deleteUser(uid);
    }

    /**
     * 管理员级别
     *
     * @param user
     */
    @PostMapping("updateRole")
    public void uodateUserRole(@RequestBody User user) {
        userService.updateRole(user.getNum(), user.getRole());
    }

}
