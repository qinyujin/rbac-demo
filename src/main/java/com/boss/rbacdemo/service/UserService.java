package com.boss.rbacdemo.service;

import com.boss.rbacdemo.entity.User;

/**
 * @author :覃玉锦
 * @create :2020-08-03 19:48:00
 */
public interface UserService {

    User getUserByNum(int num);

    /**
     * 查看自己信息
     *
     * @return
     */
    User querySelfInfo();

    /**
     * 更新自己昵称
     *
     * @param newName
     */
    void updateName(int num, String newName);

    /**
     * 新增用户，管理员级别
     *
     * @param user
     */
    void saveUser(User user);

    /**
     * 删除用户，管理员级别
     *
     * @param num
     */
    void deleteUser(int num);

    /**
     * 更新角色信息，管理员级别
     *
     * @param num
     * @param role
     */
    void updateRole(int num, int role);
}
