package com.boss.rbacdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boss.rbacdemo.dao.MenuDao;
import com.boss.rbacdemo.dao.RoleDao;
import com.boss.rbacdemo.entity.Role;
import com.boss.rbacdemo.service.RoleService;
import com.boss.rbacdemo.service.dto.RoleMenuDTO;
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

    @Autowired
    private MenuDao menuDao;


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

    /**
     * 搁置
     * @param dto
     * @return
     */
    @Override
    public Integer setMenu(RoleMenuDTO dto) {
        /*return roleDao.setMenu(dto.getRid(), dto.getMid());*/
        return null;
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
