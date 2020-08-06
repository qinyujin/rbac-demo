package com.boss.rbacdemo.contoller;

import com.boss.rbacdemo.entity.Menu;
import com.boss.rbacdemo.entity.Role;
import com.boss.rbacdemo.service.MenuService;
import com.boss.rbacdemo.service.RoleService;
import com.boss.rbacdemo.service.dto.RoleMenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author :覃玉锦
 * @create :2020-08-05 21:07:00
 */
@RestController
@RequestMapping("/api/role/")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    @GetMapping("listRole")
    public Map getRoleList(){
        List<Role> roles = roleService.getRoles();
        return Map.of("roles",roles);
    }

    @PostMapping("saveRole")
    public Map saveRole(@RequestBody Role role){
        roleService.saveRole(role);

        return Map.of("save role",role);
    }


    @PostMapping("deleteRole")
    public Map deleteRole(@RequestBody RoleMenuDTO rmd){
        Role role = roleService.getRoleById(rmd.getRid());
        roleService.deleteRoleById(rmd.getRid());
        return Map.of("delete role",role);
    }


    @PostMapping("setMenu")
    public Map setMenu(@RequestBody RoleMenuDTO rmd){
         roleService.setMenu(rmd);
        Menu menu = menuService.getMenuById(rmd.getMid());
        return Map.of("set menu",menu);
    }

    @PostMapping("deleteMenu")
    public  Map deleteMenu(@RequestBody RoleMenuDTO rmd){
        roleService.deleteMenu(rmd);
        Menu menu = menuService.getMenuById(rmd.getMid());
        return Map.of("delete menu",menu);
    }

}
