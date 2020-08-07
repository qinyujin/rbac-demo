package com.boss.rbacdemo.contoller;

import com.boss.rbacdemo.entity.CommonResult;
import com.boss.rbacdemo.entity.Menu;
import com.boss.rbacdemo.entity.Role;
import com.boss.rbacdemo.entity.dto.RoleMenuDTO;
import com.boss.rbacdemo.service.MenuService;
import com.boss.rbacdemo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2020-08-05 21:07:00
 * 角色管理
 */
@CrossOrigin
@RestController
@RequestMapping("/api/role/")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @GetMapping("listRole")
    public CommonResult getRoleList() {
        List<Role> roles = roleService.getRoles();
        return new CommonResult(200,"角色列表",roles);
    }

    @PostMapping("saveRole")
    public CommonResult saveRole(@RequestBody Role role) {
        roleService.saveRole(role);

        return new CommonResult(200, "添加角色"+role.getName()+"成功" );
    }

    @PostMapping("deleteRole")
    public CommonResult deleteRole(@RequestBody RoleMenuDTO rmd) {
        Role role = roleService.getRoleById(rmd.getRid());
        roleService.deleteRoleById(rmd.getRid());
        return new CommonResult(200,"删除角色"+role.getName()+"成功" );
    }

    @PostMapping("setMenu")
    public CommonResult setMenu(@RequestBody RoleMenuDTO rmd) {
        roleService.setMenu(rmd);
        Menu menu = menuService.getMenuById(rmd.getMid());
        return new CommonResult(200,"设置角色"+roleService.getRoleById(rmd.getRid())
        +"的菜单为"+menu.getName()
        );
    }

    @PostMapping("deleteMenu")
    public CommonResult deleteMenu(@RequestBody RoleMenuDTO rmd) {
        roleService.deleteMenu(rmd);
        Menu menu = menuService.getMenuById(rmd.getMid());
        return new CommonResult(200,"删除角色"+roleService.getRoleById(rmd.getRid())
        +"的菜单"+menu.getName() );
    }

}
