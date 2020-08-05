package com.boss.rbacdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boss.rbacdemo.dao.MenuDao;
import com.boss.rbacdemo.entity.Menu;
import com.boss.rbacdemo.service.MenuService;
import com.boss.rbacdemo.service.dto.MenuPermissionDTO;
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
    public List<Menu> getMenus() {
return         menuDao.selectList(new QueryWrapper<>());
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
        return menuDao.setPermission(dto.getMid(), dto.getPid());
    }
}
