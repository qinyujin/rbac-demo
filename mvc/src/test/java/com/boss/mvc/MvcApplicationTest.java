package com.boss.mvc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boss.mvc.dao.MenuDao;
import com.boss.mvc.dao.UserDao;
import com.boss.mvc.entity.Menu;
import com.boss.mvc.entity.User;
import com.boss.mvc.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2020-08-15 17:20:00
 */
@SpringBootTest
class MvcApplicationTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private UserService userService;

    @Test
    void UserDaoTest() {
        System.out.println(userDao.getUserByName("Qin"));
        List<User> users = userDao.selectList(new QueryWrapper<>());
        System.out.println(users);
        System.out.println(userDao.getRole(1));
    }

    @Test
    void setUserServiceTest(){
        List<User> users = userService.getUsers();
        System.out.println(users);
    }

    @Test
    void MenuDaoTest(){
        List<Menu> menus = menuDao.selectList(new QueryWrapper<>());
        System.out.println(menus);
    }
}