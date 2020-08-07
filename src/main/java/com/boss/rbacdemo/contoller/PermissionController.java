package com.boss.rbacdemo.contoller;

import com.boss.rbacdemo.entity.CommonResult;
import com.boss.rbacdemo.entity.Permission;
import com.boss.rbacdemo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public CommonResult listPermission() {
        List<Permission> permissiones = permissionService.getPermissiones();

        return new CommonResult(200,"权限列表", permissiones);
    }

    @PostMapping("savePermission")
    public CommonResult savePermission(@RequestBody Permission permission) {
        permissionService.savePermission(permission);
        Permission permission1 = permissionService.getPermissionByName(permission.getName());
        return new CommonResult(200,"添加权限", permission1);
    }

    @PostMapping("deletePermission")
    public CommonResult deletePermission(@RequestBody Permission permission) {
        Permission permission1 = permissionService.getPermissionById(permission.getId());
        permissionService.deletePermissionById(permission.getId());
        return new CommonResult(200,"删除权限", permission1);
    }
}
