package com.boss.rbacdemo.service.impl;

import com.boss.rbacdemo.component.RequestComponent;
import com.boss.rbacdemo.dao.UserDao;
import com.boss.rbacdemo.entity.User;
import com.boss.rbacdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author :覃玉锦
 * @create :2020-08-03 20:53:00
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RequestComponent requestComponent;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public User getUserByNum(int num) {
        return userDao.getUserByNum(num);
    }

    @Override
    public User querySelfInfo() {
        int uid = requestComponent.getUid();
        return userDao.getUserByNum(uid);
    }

    @Override
    public void updateName(int num, String newName) {
        User user = new User();
        user.setNum(num);
        user.setName(newName);
        userDao.updateUserName(user);
    }

    @Override
    public void saveUser(User user) {
        String encode = encoder.encode(user.getPassword());
        user.setPassword(encode);
        userDao.saveUser(user);
    }

    @Override
    public void deleteUser(int num) {
        userDao.deleteUserByNum(num);
    }

    @Override
    public void updateRole(int num, int role) {
        User user = new User();
        user.setNum(num);
        user.setRole(role);
        userDao.updateUserRole(user);
    }
}
