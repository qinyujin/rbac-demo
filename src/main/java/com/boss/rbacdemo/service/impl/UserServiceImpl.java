package com.boss.rbacdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boss.rbacdemo.component.RequestComponent;
import com.boss.rbacdemo.dao.UserDao;
import com.boss.rbacdemo.dao.po.UserRolePO;
import com.boss.rbacdemo.entity.User;
import com.boss.rbacdemo.service.UserService;
import com.boss.rbacdemo.service.dto.UserDTO;
import com.boss.rbacdemo.service.dto.UserRoleDTO;
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
        log.debug("编码之前的密码：{}",user.getPassword());
        String encode = encoder.encode(user.getPassword());
        log.debug("密码加密成功{}",encode );
        user.setPassword(encode);
        int insert = userDao.insert(user);
        User u = userDao.getUserByName(user.getName());
//        默认角色为员工
        /*UserRolePO urp = new UserRolePO(u.getId(),2);
        userDao.setRole(urp);*/
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
    public Integer updatePassword(UserDTO userDTO) {
        int uid = requestComponent.getUid();
        User user = userDao.selectById(uid);
        String newPwd = encoder.encode(userDTO.getPassword());
        user.setPassword(newPwd);
        return userDao.updateById(user);
    }

    @Override
    public Integer deleteUserById(int id) {
        return userDao.deleteById(id);
    }
}
