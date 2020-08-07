package com.boss.rbacdemo.contoller;

import com.boss.rbacdemo.component.RequestComponent;
import com.boss.rbacdemo.entity.CommonResult;
import com.boss.rbacdemo.entity.Menu;
import com.boss.rbacdemo.entity.Permission;
import com.boss.rbacdemo.entity.dto.MenuPermissionDTO;
import com.boss.rbacdemo.service.MenuService;
import com.boss.rbacdemo.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2020-08-06 09:45:00
 * 菜单管理
 */
@CrossOrigin
@RestController
@RequestMapping("/api/menu/")
@Slf4j
public class MenuController {
    @Autowired
    private MenuService menuService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RequestComponent requestComponent;

    @GetMapping("listMenu")
    public CommonResult listMenu() {
        List<Menu> menus = menuService.getMenus();

        return new CommonResult(200,"菜单列表", menus);
    }

    @PostMapping("saveMenu")
    public CommonResult saveMenu(@RequestBody Menu menu) {
        menuService.saveMenu(menu);
        Menu m = menuService.getMenuByName(menu.getName());
        return new CommonResult(200,"添加菜单", m);
    }

    @PostMapping("deleteMenu")
    public CommonResult deleteMenu(@RequestBody Menu menu) {
        Menu m = menuService.getMenuById(menu.getId());
        menuService.deleteMenuById(menu.getId());
        return new CommonResult(200,"删除菜单："+m.getName());
    }

    @PostMapping("setPermission")
    public CommonResult setPermission(@RequestBody MenuPermissionDTO mpd) {
        menuService.setPermission(mpd);
        Permission permission = permissionService.getPermissionById(mpd.getPid());
        return new CommonResult(200,"设置菜单"+menuService.getMenuById(mpd.getMid())
        +"添加权限"+permission.getName());
    }

    @PostMapping("deletePermisson")
    public CommonResult deletePermission(@RequestBody MenuPermissionDTO mpd) {
        menuService.deletePermission(mpd);
        Permission permission = permissionService.getPermissionById(mpd.getPid());
        return new CommonResult(200, "删除菜单"+menuService.getMenuById(mpd.getMid())
        +"的权限："+permission.getName());
    }

    /**
     * 返回用户对应的菜单的url，前端就是根据不同的url来显示不同的页面
     *
     * @return
     */
    @GetMapping("getUserMenu")
    public CommonResult getUserMenu() {
        int role = requestComponent.getRole();
        log.debug("Role in MenuController:{}", role);
        String menuUrl = menuService.getRoleMenu(role);
        String urlHead = "http://127.0.0.1:8080/";
        return new CommonResult(200,"当前用户菜单地址",urlHead+menuUrl);
    }
}
