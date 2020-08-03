package com.boss.rbacdemo.dao;

import com.boss.rbacdemo.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author :覃玉锦
 * @create :2020-08-03 19:41:00
 * 角色无法修改，只能给用户分配角色。
 */
@Mapper
public interface RoleDao {
    /**
     * 通过名称获取权限
     * @param detail
     * @return
     */
    Role getRoleByDetail(String detail);

    /**
     * 通过id获取权限
     * @param rid
     * @return
     */
    String getRoleById(int rid);
}
