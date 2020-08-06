package com.boss.rbacdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boss.rbacdemo.dao.po.RoleMenuPO;
import com.boss.rbacdemo.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2020-08-03 19:41:00
 */
@Mapper
public interface RoleDao extends BaseMapper<Role> {
    @Select("select id,name from role where name=#{name}")
    Role getRoleByName(String name);

    @Insert("insert into role_menu values(#{rid},#{mid})")
    Integer setMenu(RoleMenuPO roleMenuPO);

    @Delete("delete from role_menu where rid=#{rid} and mid=#{mid}")
    Integer deleteMenu(RoleMenuPO roleMenuPO);

    @Select("SELECT mid FROM role_menu WHERE rid=#{rid}")
    List<Integer> getMenus(int rid);

}
