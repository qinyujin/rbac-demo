package com.boss.rbacdemo.contoller;

import com.boss.rbacdemo.entity.Permission;
import com.boss.rbacdemo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author :覃玉锦
 * @create :2020-08-06 10:10:00
 * 权限管理
 */
@CrossOrigin
@RestController
@RequestMapping("/api/permission/")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping("listPermission")
    public Map listPermission() {
        List<Permission> permissiones = permissionService.getPermissiones();

        return Map.of("permissiones", permissiones);
    }

    @PostMapping("savePermission")
    public Map savePermission(@RequestBody Permission permission) {
        permissionService.savePermission(permission);
        Permission permission1 = permissionService.getPermissionByName(permission.getName());
        return Map.of("permission", permission1);
    }

    @PostMapping("deletePermission")
    public Map deletePermission(@RequestBody Permission permission) {
        Permission permission1 = permissionService.getPermissionById(permission.getId());
        permissionService.deletePermissionById(permission.getId());
        return Map.of("delete permission", permission1);
    }
}
