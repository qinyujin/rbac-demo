package com.boss.mvc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boss.mvc.entity.User;
import com.boss.mvc.entity.po.UserRolePO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * @author :覃玉锦
 * @create :2020-08-03 18:09:00
 * 对User的crud
 */
public interface UserDao extends BaseMapper<User> {
    /**
     * 获取用户
     *
     * @param name
     * @return
     */
    @Select("select id,name,password from user where name=#{name}")
    User getUserByName(String name);

    /**
     * 删除用户的角色
     *
     * @param userRolePO
     * @return
     */
    @Delete("delete from user_role where uid=#{uid} and rid=#{rid}")
    Integer deleteRole(UserRolePO userRolePO);

    /**
     * 设置角色
     *
     * @param userRolePO
     * @return
     */
    @Insert("insert into user_role values(#{uid},#{rid})")
    Integer setRole(UserRolePO userRolePO);

    /**
     * 获取角色
     *
     * @param uid
     * @return
     */
    @Select("select rid from user_role where uid=#{uid}")
    Integer getRole(int uid);

}
