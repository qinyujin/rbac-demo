package com.boss.rbacdemo.service;

import com.boss.rbacdemo.entity.Permission;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2020-08-03 19:48:00
 * rbac的基本功能都完成了,包括用户管理,角色管理,菜单管理,权限管理
 * 此类为用户管理
 */
public interface PermissionService {
    /**
     * 获取权限列表
     *
     * @return
     */
    List<Permission> getPermissiones();

    /**
     * 获取权限
     *
     * @param id
     * @return
     */
    Permission getPermissionById(int id);

    /**
     * 获取权限
     * @param name
     * @return
     */
    Permission getPermissionByName(String name);

    /**
     * 添加权限
     *
     * @param permission
     * @return
     */
    Integer savePermission(Permission permission);

    /**
     * 删除权限
     *
     * @param id
     * @return
     */
    Integer deletePermissionById(int id);

}
