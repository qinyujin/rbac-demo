package com.boss.rbac.controller;

import com.boss.component.component.EncryptComponent;
import com.boss.component.component.MyToken;
import com.boss.component.component.RedisUtil;
import com.boss.mvc.entity.CommonResult;
import com.boss.mvc.entity.User;
import com.boss.mvc.entity.dto.UserDTO;
import com.boss.mvc.entity.dto.UserRoleDTO;
import com.boss.mvc.entity.vo.UserRoleVO;
import com.boss.mvc.entity.vo.UserVO;
import com.boss.mvc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2020-08-03 21:33:00
 * 用户端口，实现权限区分，部分接口只有权限通过才能访问
 * 访问控制:根据权限判断用户是否具有访问api的权限
 * 页面元素:通过返回的url来获取用户相应页面
 * vo：我理解的vo就是看前端需要什么数据，把这些属性封装到一个类里，然后返回，即view object。vo只能在前端和controller层流动
 */
@CrossOrigin
@RestController
@RequestMapping("/api/user/")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private EncryptComponent encryptComponent;

    /**
     * 查看用户列表
     *
     * @return
     */
    @GetMapping("listUser")
    public CommonResult getUserList() {
        List<User> users = userService.getUsers();
        if (!users.isEmpty()) {
            return new CommonResult(200, "用户列表", users);
        } else {
            return new CommonResult(401, "没有用户");
        }
    }

    /**
     * 增添用户
     *
     * @param user * @return
     */
    @PostMapping("saveUser")
    public CommonResult saveUser(@RequestBody User user) {
        log.debug("SaveUser json User{}", user);
        Integer saveValue = userService.saveUser(user);
        if (saveValue == 0) {
            return new CommonResult(400, "请勿重复添加用户");
        }
        return new CommonResult(200, "添加用户成功", user);
    }

    /**
     * 更新当前用户密码
     * password
     *
     * @param userVO
     * @return
     */
    @PostMapping("updatePassword")
    public CommonResult updatePassword(@RequestBody UserVO userVO) {
        log.info("进入controller");
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userVO, userDTO);
        Integer updateValue = userService.updatePassword(userDTO);
        //从redis中拿到auth，将auth解密后得到token。
        //User newUser = userService.getUserById(requestComponent.getUid());

        String auth = (String) redisUtil.get(MyToken.AUTHORIZATION);
        MyToken myToken = encryptComponent.decryptToken(auth);
        User newUser = userService.getUserById(myToken.getUid());
        //
        log.debug("更新密码，拿到返回值newUSer：{}", newUser);
        log.info("退出controller");
        if (updateValue == 0) {
            return new CommonResult(400, "密码重复，请输入新密码");
        }
        return new CommonResult(200, "更新密码成功", "新密码：" + userVO.getPassword());
    }

    /**
     * 获取当前用户信息
     *
     * @return
     */
    @GetMapping("getUser")
    public CommonResult getUserById() {
        //
//        int uid = requestComponent.getUid();
        String auth = (String) redisUtil.get(MyToken.AUTHORIZATION);
        MyToken myToken = encryptComponent.decryptToken(auth);
        int uid = myToken.getUid();
        //

        User user = userService.getUserById(uid);
        if (user != null) {
            return new CommonResult(200, "用户信息", user);
        } else {
            return new CommonResult(400, "服务器错误");
        }
    }

    /**
     * 设置角色
     *
     * @param urv
     * @return
     */
    @PostMapping("setRole")
    public CommonResult setRole(@RequestBody UserRoleVO urv) {
        UserRoleDTO urd = new UserRoleDTO();
        BeanUtils.copyProperties(urv, urd);
        Integer setValue = userService.setRole(urd);
        User user = userService.getUserById(urd.getUid());
        if (setValue == 1) {
            return new CommonResult(200, "设置用户" + user.getName() + "的角色为",
                    userService.getRole(user.getId()));
        } else {
            return new CommonResult(500, "服务器异常");
        }
    }

    /**
     * 删除角色
     *
     * @param userRoleVO
     * @return
     */
    @PostMapping("deleteRole")
    public CommonResult deleteRole(@RequestBody UserRoleVO userRoleVO) {
        UserRoleDTO urd = new UserRoleDTO();
        BeanUtils.copyProperties(userRoleVO, urd);
        Integer deleteValue = userService.deleteRole(urd);
        User user = userService.getUserById(urd.getUid());
        if (deleteValue == 1) {
            return new CommonResult(200, "删除角色成功"
            );
        } else {
            return new CommonResult(500, "服务器异常");
        }
    }

    /**
     * 删除用户
     *
     * @param userRoleVO
     * @return
     */
    @PostMapping("deleteUser")
    public CommonResult deleteUser(@RequestBody UserRoleVO userRoleVO) {
        UserRoleDTO urd = new UserRoleDTO();
        BeanUtils.copyProperties(userRoleVO, urd);
        User user = userService.getUserById(urd.getUid());
        Integer integer = userService.deleteUserById(urd.getUid());
        if (integer == 1) {
            return new CommonResult(200, "删除用户" + user.getName() + "成功");
        } else {
            return new CommonResult(500, "服务器异常");
        }
    }

}
