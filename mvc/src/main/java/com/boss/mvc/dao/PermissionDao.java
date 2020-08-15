package com.boss.mvc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boss.mvc.entity.Permission;
import org.apache.ibatis.annotations.Select;

/**
 * @author :覃玉锦
 * @create :2020-08-05 09:22:00
 */
public interface PermissionDao extends BaseMapper<Permission> {
    /**
     * 获取权限
     * @param name
     * @return
     */
    @Select("select id,name,url from permission where name=#{name}")
    Permission getPermissionByName(String name);
}
