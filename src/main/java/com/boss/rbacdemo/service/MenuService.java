package com.boss.rbacdemo.service;

import com.boss.rbacdemo.entity.Menu;
import com.boss.rbacdemo.service.dto.MenuPermissionDTO;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2020-08-03 19:48:00
 * rbac的基本功能都完成了,包括用户管理,角色管理,菜单管理,权限管理
 * 此类为用户管理
 */
public interface MenuService {
    /**
     * 获取菜单列表
     * @return
     */
    List<Menu> getMenus();

    /**
     * 获取菜单
     * @param id
     * @return
     */
    Menu getMenuById(int id);

    /**
     * 添加菜单
     * @param menu
     * @return
     */
    Integer saveMenu(Menu menu);

    /**
     * 删除菜单
     * @param id
     * @return
     */
    Integer deleteMenuById(int id);

    /**
     * 设置权限
     * @param dto
     * @return
     */
    Integer setPermission(MenuPermissionDTO dto);
}
