package com.boss.rbacdemo.service;

import com.boss.rbacdemo.entity.Role;
import com.boss.rbacdemo.service.dto.RoleMenuDTO;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2020-08-03 19:48:00
 * rbac的基本功能都完成了,包括用户管理,角色管理,菜单管理,权限管理
 * 此类为角色管理
 */
public interface RoleService {
    /**
     * 获取角色列表
     *
     * @return
     */
    List<Role> getRoles();

    /**
     * 通过id获取角色
     *
     * @param id
     * @return
     */
    Role getRoleById(int id);

    /**
     * 获取角色
     *
     * @param roleName
     * @return
     */
    Role getRoleByName(String roleName);

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    Integer saveRole(Role role);

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    Integer deleteRoleById(int id);

    /**
     * 设置菜单
     *
     * @param dto
     * @return
     */
    Integer setMenu(RoleMenuDTO dto);

    /**
     * 删除菜单
     *
     * @param dto
     * @return
     */
    Integer deleteMenu(RoleMenuDTO dto);

    /**
     * 获取角色对应菜单
     *
     * @param rid
     * @return
     */
    List<Integer> getMenu(int rid);

}
