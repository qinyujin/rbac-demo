package com.boss.mvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boss.mvc.dao.UserDao;
import com.boss.mvc.entity.User;
import com.boss.mvc.entity.dto.UserDTO;
import com.boss.mvc.entity.dto.UserRoleDTO;
import com.boss.mvc.entity.po.UserRolePO;
import com.boss.mvc.service.UserService;
import com.boss.mvc.util.EncryptUtil;
import com.boss.mvc.util.MvcMyToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

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
    private EncryptUtil encryptUtil;

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
        log.debug("进入saveUser");
        String encode = encoder.encode(user.getPassword());
        User uDao = userDao.getUserByName(user.getName());
        if (uDao != null) {
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
    public Integer updatePassword(UserDTO userDTO) {
        log.info("进入service");
        //由于不能循环依赖，在这里就不使用component组件了，直接使用redis
//        int uid = requestComponent.getUid();
        Jedis jedis = new Jedis();
        jedis.auth("123456");
        String auth = jedis.get(MvcMyToken.AUTHORIZATION);
        //auth的引号得去掉
        String realAuth = auth.substring(1, auth.length() - 1);
        log.info("auth:"+auth);
        MvcMyToken mvcMyToken = encryptUtil.decryptToken(realAuth);
        log.info("Mytoken:"+mvcMyToken.toString());
        int uid = mvcMyToken.getUid();
        //
        User user = userDao.selectById(uid);
        boolean repeat = encoder.matches(userDTO.getPassword(), user.getPassword());
        if (repeat) {
            return 0;
        }
        String newPwd = encoder.encode(userDTO.getPassword());
        user.setPassword(newPwd);
        log.info("退出service");
        return userDao.updateById(user);
    }

    @Override
    public Integer deleteUserById(int id) {
        return userDao.deleteById(id);
    }
}
