package com.boss.rbacdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boss.rbacdemo.component.RequestComponent;
import com.boss.rbacdemo.dao.UserDao;
import com.boss.rbacdemo.entity.User;
import com.boss.rbacdemo.entity.dto.UserDTO;
import com.boss.rbacdemo.entity.dto.UserRoleDTO;
import com.boss.rbacdemo.entity.po.UserRolePO;
import com.boss.rbacdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2020-08-03 20:53:00
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private RequestComponent requestComponent;

    @Override
    public List<User> getUsers() {
        return userDao.selectList(new QueryWrapper<>());
    }

    @Override
    public Integer setRole(UserRoleDTO userRoleDTO) {
        UserRolePO urp = new UserRolePO();
        BeanUtils.copyProperties(userRoleDTO, urp);
        return userDao.setRole(urp);
    }

    @Override
    public User getUserById(int id) {
        return userDao.selectById(id);
    }

    @Override
    public Integer saveUser(User user) {
        log.debug("进入saveUser" );
        String encode = encoder.encode(user.getPassword());
        User uDao = userDao.getUserByName(user.getName());
        if(uDao!=null){
            return 0;
        }
        user.setPassword(encode);
        int insert = userDao.insert(user);
        User u = userDao.getUserByName(user.getName());
        return insert;
    }

    @Override
    public Integer getRole(int uid) {
        return userDao.getRole(uid);
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    public Integer deleteRole(UserRoleDTO userRoleDTO) {
        UserRolePO urp = new UserRolePO();
        BeanUtils.copyProperties(userRoleDTO, urp);
        return userDao.deleteRole(urp);
    }

    @Override
    public Integer updatePassword(UserDTO userDTO){
        int uid = requestComponent.getUid();
        User user = userDao.selectById(uid);
        boolean repeat = encoder.matches(userDTO.getPassword(), user.getPassword());
        if(repeat){
            return 0;
        }
        String newPwd = encoder.encode(userDTO.getPassword());
        user.setPassword(newPwd);
        return userDao.updateById(user);
    }

    @Override
    public Integer deleteUserById(int id) {
        return userDao.deleteById(id);
    }
}
