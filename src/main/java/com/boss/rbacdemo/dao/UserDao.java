package com.boss.rbacdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boss.rbacdemo.entity.User;
import org.apache.ibatis.annotations.*;

/**
 * @author :覃玉锦
 * @create :2020-08-03 18:09:00
 * 对Employee的crud
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
    @Select("select id,name,password from user where name=#{name}")
    User getUserByName(String name);

    @Delete("delete from user_role where uid=#{uid} and rid=#{rid}")
    Integer deleteRole(int uid,int rid);

    @Insert("insert into user_role values(#{uid},#{rid})")
    Integer setRole(int uid,int rid);

    @Select("select rid from user_role where uid=#{uid}")
    int getRole(int uid);

}
