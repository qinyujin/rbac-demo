package com.boss.rbacdemo.contoller;

import com.boss.rbacdemo.component.RequestComponent;
import com.boss.rbacdemo.entity.Menu;
import com.boss.rbacdemo.entity.Permission;
import com.boss.rbacdemo.service.MenuService;
import com.boss.rbacdemo.service.PermissionService;
import com.boss.rbacdemo.service.dto.MenuPermissionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author :覃玉锦
 * @create :2020-08-06 09:45:00
 * 菜单管理
 */
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
    public Map listMenu() {
        List<Menu> menus = menuService.getMenus();

        return Map.of("menus", menus);
    }

    @PostMapping("saveMenu")
    public Map saveMenu(@RequestBody Menu menu) {
        menuService.saveMenu(menu);
        Menu m = menuService.getMenuByName(menu.getName());
        return Map.of("save menu", m);
    }

    @PostMapping("deleteMenu")
    public Map deleteMenu(@RequestBody Menu menu) {
        Menu m = menuService.getMenuById(menu.getId());
        menuService.deleteMenuById(menu.getId());
        return Map.of("delete menu", m);
    }

    @PostMapping("setPermission")
    public Map setPermission(@RequestBody MenuPermissionDTO mpd) {
        menuService.setPermission(mpd);
        Permission permission = permissionService.getPermissionById(mpd.getPid());
        return Map.of("set permission", permission);
    }

    @PostMapping("deletePermisson")
    public Map deletePermission(@RequestBody MenuPermissionDTO mpd) {
        menuService.deletePermission(mpd);
        Permission permission = permissionService.getPermissionById(mpd.getPid());
        return Map.of("delete permission", permission);
    }

    /**
     * 返回用户对应的菜单的url，前端就是根据不同的url来显示不同的页面
     *
     * @return
     */
    @GetMapping("getUserMenu")
    public Map getUserMenu() {
        int role = requestComponent.getRole();
        log.debug("Role in MenuController:{}", role);
        String menuUrl = menuService.getRoleMenu(role);
        String urlHead = "http://127.0.0.1:8080/";
        return Map.of("跳转到页面:", urlHead + menuUrl);
    }
}
