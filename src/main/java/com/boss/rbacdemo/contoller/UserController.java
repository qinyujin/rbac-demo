package com.boss.rbacdemo.contoller;

import com.boss.rbacdemo.component.RequestComponent;
import com.boss.rbacdemo.component.TransferToUserVOComponent;
import com.boss.rbacdemo.contoller.vo.UserVO;
import com.boss.rbacdemo.entity.User;
import com.boss.rbacdemo.service.UserService;
import com.boss.rbacdemo.service.dto.UserDTO;
import com.boss.rbacdemo.service.dto.UserRoleDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @Autowired
    private TransferToUserVOComponent transfer;

    /**
     * 查看用户列表
     *
     * @return
     */
    @GetMapping("listUser")
    public Map getUserList() {
        List<User> users = userService.getUsers();
        return Map.of("users:", users);
    }

    /**
     * 增添用户
     *
     * @param user
     * @return
     */
    @PostMapping("saveUser")
    public Map saveUser(@RequestBody User user) {
        log.debug("SaveUser json User{}", user);
        userService.saveUser(user);
        UserVO userVO = transfer.transferToVO(user);
        return Map.of("user", userVO);
    }

    /**
     * 更新当前用户密码
     *
     * @param userDTO
     * @return
     */
    @PostMapping("updatePassword")
    public Map updatePassword(@RequestBody UserDTO userDTO) {
        userService.updatePassword(userDTO);
        User newUser = userService.getUserById(userDTO.getId());
        UserVO userVO = transfer.transferToVO(newUser);
        return Map.of("newUser", userVO);
    }

    /**
     * 获取当前用户信息
     *
     * @return
     */
    @GetMapping("getUser")
    public Map getUserById() {
        int uid = requestComponent.getUid();
        User user = userService.getUserById(uid);
        UserVO userVO = transfer.transferToVO(user);
        return Map.of("user", userVO);
    }

    /**
     * 设置角色
     *
     * @param urd
     * @return
     */
    @PostMapping("setRole")
    public Map setRole(@RequestBody UserRoleDTO urd) {
        userService.setRole(urd);
        User user = userService.getUserById(urd.getUid());
        UserVO userVO = transfer.transferToVO(user);
        return Map.of("user", userVO);
    }

    /**
     * 删除角色
     *
     * @param urd
     * @return
     */
    @PostMapping("deleteRole")
    public Map deleteRole(@RequestBody UserRoleDTO urd) {
        userService.deleteRole(urd);
        User user = userService.getUserById(urd.getUid());
        UserVO userVO = transfer.transferToVO(user);
        return Map.of("user", userVO);
    }

    /**
     * 删除用户
     *
     * @param urd
     * @return
     */
    @PostMapping("deleteUser")
    public Map deleteUser(@RequestBody UserRoleDTO urd) {
        userService.getUserById(urd.getUid());
        userService.deleteUserById(urd.getUid());
        return Map.of("deletedUser", "成功");
    }

}
