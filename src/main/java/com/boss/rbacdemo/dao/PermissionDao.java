package com.boss.rbacdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boss.rbacdemo.entity.Permission;
import org.apache.ibatis.annotations.Select;

/**
 * @author :覃玉锦
 * @create :2020-08-05 09:22:00
 */
public interface PermissionDao extends BaseMapper<Permission> {
    @Select("select id,name,url from permission where name=#{name}")
    Permission getPermissionByName(String name);
}
