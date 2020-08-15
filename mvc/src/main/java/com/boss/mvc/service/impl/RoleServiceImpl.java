package com.boss.mvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boss.mvc.dao.RoleDao;
import com.boss.mvc.entity.Role;
import com.boss.mvc.entity.dto.RoleMenuDTO;
import com.boss.mvc.entity.po.RoleMenuPO;
import com.boss.mvc.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2020-08-05 12:24:00
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public Integer deleteMenu(RoleMenuDTO dto) {
        RoleMenuPO rmp = new RoleMenuPO();
        BeanUtils.copyProperties(dto, rmp);
        return roleDao.deleteMenu(rmp);
    }

    @Override
    public List<Role> getRoles() {
        return roleDao.selectList(new QueryWrapper<>());
    }

    @Override
    public Role getRoleById(int id) {
        return roleDao.selectById(id);
    }

    @Override
    public Integer saveRole(Role role) {
        return roleDao.insert(role);
    }

    @Override
    public Integer setMenu(RoleMenuDTO dto) {
        RoleMenuPO rmp = new RoleMenuPO();
        BeanUtils.copyProperties(dto, rmp);
        return roleDao.setMenu(rmp);
    }

    @Override
    public List<Integer> getMenu(int rid) {
        return roleDao.getMenus(rid);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleDao.getRoleByName(roleName);
    }

    @Override
    public Integer deleteRoleById(int id) {
        return roleDao.deleteById(id);
    }
}
