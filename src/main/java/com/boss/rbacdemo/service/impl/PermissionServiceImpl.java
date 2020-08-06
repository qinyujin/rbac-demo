package com.boss.rbacdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boss.rbacdemo.dao.PermissionDao;
import com.boss.rbacdemo.entity.Permission;
import com.boss.rbacdemo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2020-08-05 12:41:00
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Permission> getPermissiones() {
        return permissionDao.selectList(new QueryWrapper<>());
    }

    @Override
    public Permission getPermissionById(int id) {
        return permissionDao.selectById(id);
    }

    @Override
    public Permission getPermissionByName(String name) {
        return permissionDao.getPermissionByName(name);
    }

    @Override
    public Integer savePermission(Permission permission) {
        return permissionDao.insert(permission);
    }

    @Override
    public Integer deletePermissionById(int id) {
        return permissionDao.deleteById(id);
    }
}
