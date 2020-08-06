package com.boss.rbacdemo.component;

import com.boss.rbacdemo.entity.Menu;
import com.boss.rbacdemo.entity.Permission;
import com.boss.rbacdemo.entity.Role;
import com.boss.rbacdemo.entity.User;
import com.boss.rbacdemo.service.MenuService;
import com.boss.rbacdemo.service.PermissionService;
import com.boss.rbacdemo.service.RoleService;
import com.boss.rbacdemo.service.UserService;
import com.boss.rbacdemo.service.dto.MenuPermissionDTO;
import com.boss.rbacdemo.service.dto.RoleMenuDTO;
import com.boss.rbacdemo.service.dto.UserRoleDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2020-08-06 10:51:00
 */
@Component
@Slf4j
public class InitComponent implements InitializingBean {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void afterPropertiesSet() throws Exception {
        //初始化一个菜单
        String initMenu = "超级管理员菜单";
        Menu menu = menuService.getMenuByName(initMenu);
        if (menu == null) {
            Menu m = new Menu();
            m.setId(1);
            m.setUrl("/superadmin");
            m.setName(initMenu);
            m.setParentId(0);
            menuService.saveMenu(m);
        }

        //初始化一个角色
        String initRole = "超级管理员";
        Role role = roleService.getRoleByName(initRole);
        if (role == null) {
            Role r = new Role();
            r.setId(1);
            r.setName("超级管理员");
            roleService.saveRole(r);
        }

        //初始化超级用户,用户名：Qin,密码：123456
        String initName = "Qin";
        String initPassword = "123456";
        User user = userService.getUserByName(initName);
        if (user == null) {
            User u = new User();
            u.setId(1);
            u.setName(initName);
            u.setPassword(initPassword);
            log.debug("刚存入的密码比对：{}", encoder.matches("123465", u.getPassword()));
            userService.saveUser(u);
        }

        //将初始用户角色置为超级管理员
        Integer userRole = userService.getRole(1);
        if (userRole == null) {
            UserRoleDTO urd = new UserRoleDTO();
            urd.setUid(1);
            urd.setRid(1);
            userService.setRole(urd);
        }

        //初始化超级管理角色一个管理员菜单
        List<Integer> menus = roleService.getMenu(1);
        if (menus.isEmpty()) {
            RoleMenuDTO rmd = new RoleMenuDTO();
            rmd.setRid(1);
            rmd.setMid(1);
            roleService.setMenu(rmd);
        }

        //初始化管理员菜单拥有所有全新啊
        List<Integer> permissiones = menuService.getPermissiones(1);
        if (permissiones.isEmpty()) {
            List<Permission> permissiones1 = permissionService.getPermissiones();
            MenuPermissionDTO mpd = new MenuPermissionDTO();
            for (Permission permission : permissiones1) {
                mpd.setMid(1);
                mpd.setPid(permission.getId());
                menuService.setPermission(mpd);
            }
        }

    }
}
