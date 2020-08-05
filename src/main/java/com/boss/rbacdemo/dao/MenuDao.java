package com.boss.rbacdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boss.rbacdemo.entity.Menu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2020-08-05 09:21:00
 */
public interface MenuDao extends BaseMapper<Menu> {
    @Delete("delete from menu_permission where mid=#{mid} and pid=#{pid}")
    Integer deletePermission(int mid,int pid);

    @Insert("insert into menu_permission values(#{mid},#{pid})")
    Integer setPermission(int mid,int pid);

    @Select("select pid from menu_permission where mid =#{mid}")
    List<Integer> getPermissiones(int mid);
}
