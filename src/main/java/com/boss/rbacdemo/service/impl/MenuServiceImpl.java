package com.boss.rbacdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boss.rbacdemo.dao.MenuDao;
import com.boss.rbacdemo.entity.po.MenuPermissionPO;
import com.boss.rbacdemo.entity.Menu;
import com.boss.rbacdemo.service.MenuService;
import com.boss.rbacdemo.entity.dto.MenuPermissionDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2020-08-05 12:35:00
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Override
    public String getRoleMenu(int rid) {
        return menuDao.getRoleMenu(rid);
    }

    @Override
    public Integer deletePermission(MenuPermissionDTO mpd) {
        MenuPermissionPO mpo = new MenuPermissionPO();
        BeanUtils.copyProperties(mpd, mpo);
        return menuDao.deletePermission(mpo);
    }

    @Override
    public Menu getMenuByName(String name) {
        return menuDao.getMenuByName(name);
    }

    @Override
    public List<Menu> getMenus() {
        return menuDao.selectList(new QueryWrapper<>());
    }

    @Override
    public Menu getMenuById(int id) {
        return menuDao.selectById(id);
    }

    @Override
    public Integer saveMenu(Menu menu) {
        return menuDao.insert(menu);
    }

    @Override
    public Integer deleteMenuById(int id) {
        return menuDao.deleteById(id);
    }

    @Override
    public Integer setPermission(MenuPermissionDTO dto) {
        MenuPermissionPO mpp = new MenuPermissionPO();
        BeanUtils.copyProperties(dto, mpp);
        return menuDao.setPermission(mpp);
    }

    @Override
    public List<Integer> getPermissiones(int mid) {
        return menuDao.getPermissiones(mid);
    }
}
