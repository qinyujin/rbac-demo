package com.boss.rbacdemo.service;

import com.boss.rbacdemo.entity.User;
import com.boss.rbacdemo.service.dto.UserDTO;
import com.boss.rbacdemo.service.dto.UserRoleDTO;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2020-08-03 19:48:00
 * rbac的基本功能都完成了,包括用户管理,角色管理,菜单管理,权限管理
 * 此类为用户管理
 */
public interface UserService {
    /**
     * 获取所有用户
     *
     * @return
     */
    List<User> getUsers();

    /**
     * 根据id获取用户
     *
     * @param id
     * @return
     */
    User getUserById(int id);

    /**
     * 获取用户
     *
     * @param name
     * @return
     */
    User getUserByName(String name);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    Integer saveUser(User user);

    /**
     * 更新密码
     *
     * @param userDTO
     * @return
     */
    Integer updatePassword(UserDTO userDTO);

    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     */
    Integer deleteUserById(int id);

    /**
     * 设置角色
     *
     * @param userRoleDTO
     * @return
     */
    Integer setRole(UserRoleDTO userRoleDTO);

    /**
     * 获取用户角色
     *
     * @param uid
     * @return
     */
    Integer getRole(int uid);

    /**
     * 删除角色
     *
     * @param userRoleDTO
     * @return
     */
    Integer deleteRole(UserRoleDTO userRoleDTO);

}
