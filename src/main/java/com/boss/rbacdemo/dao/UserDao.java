package com.boss.rbacdemo.dao;

import com.boss.rbacdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2020-08-03 18:09:00
 * 对Employee的crud
 */
@Mapper
public interface UserDao {
   /**
    * 通过num获取user
    * @param num
    * @return
    */
   User getUserByNum(int num);

   /**
    * 获取所有user
    * @return
    */
   List<User> getUsers();

   /**
    * 保存user
    * @param user
    */
   void saveUser(User user);

   /**
    * 通过num删除user
    * @param num
    */
   void deleteUserByNum(int num);

   /**
    * 更新当前登录用户的姓名，不限权
    * @param user
    */
   void updateUserName(User user);

   /**
    * 更新指定用户角色，需要管理员权限
    * @param user
    */
   void updateUserRole(User user);

}
