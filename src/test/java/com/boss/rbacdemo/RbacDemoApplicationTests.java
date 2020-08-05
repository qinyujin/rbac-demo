package com.boss.rbacdemo;

import com.boss.rbacdemo.dao.MenuDao;
import com.boss.rbacdemo.dao.PermissionDao;
import com.boss.rbacdemo.dao.RoleDao;
import com.boss.rbacdemo.dao.UserDao;
import com.boss.rbacdemo.interceptor.UserInterceptor;
import com.boss.rbacdemo.service.RoleService;
import com.boss.rbacdemo.service.UserService;
import com.boss.rbacdemo.service.dto.UserRoleDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RbacDemoApplicationTests {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserInterceptor userInterceptor;

    @Test
    void testUserManager() {
//       用户列表
        /*List<User> users = userDao.selectList(new QueryWrapper<>());

        users.forEach(u ->{
            System.out.println(u);
        });*/

//        添加用户
        /*User user = new User();
        user.setName("王波");
        user.setPassword("456121");
        userDao.insert(user);
        User user1 = userDao.selectUserByName(user.getName());
//        默认给一个角色
        userDao.setRole(user1.getId(), 1);*/

//        编辑用户
        /*User user = new User();
        user.setId(0);
        user.setName("波波");
        user.setPassword("123456");
        userDao.updateById(user);
        userDao.deleteById(id);
        */

//        设置角色
        /*userDao.setRole(1, 1);
        userDao.setRole(1, 2);
        userDao.setRole(1, 3);*/
    }

    @Test
    void testRoleManager() {
//        角色列表
        /*System.out.println(roleDao.selectList(new QueryWrapper<>()));*/

//        角色添加
        /*Role role = new Role();
        role.setName("销售经理");
        roleDao.insert(role);*/

//        编辑角色
        /*Role role = new Role();
        role.setId(3);
        role.setName("销售总监");
        roleDao.updateById(role);*/

//        设置权限
        /*roleDao.setMenu(1, 2);
        roleDao.deleteMenu(1, 2);*/
    }

    @Test
    void testMenuManager() {
//        菜单列表
        /*System.out.println(menuDao.selectList(new QueryWrapper<>()));*/

//        添加菜单
        /*Menu menu = new Menu();
        menu.setName("销售经理菜单");
        menu.setUrl("/sellManager");
        menu.setParentId(2);
        menuDao.insert(menu);*/

//       菜单编辑
        /*Menu menu = new Menu();
        menu.setId(4);
        menu.setName("销售员菜单");
        menu.setUrl("/seller");
        menu.setParentId(3);
        menuDao.updateById(menu);*/

//        设置权限
        /*menuDao.setPermission(1,1);
        menuDao.setPermission(2,1);
        menuDao.setPermission(2,2);
        menuDao.setPermission(3,1);

        menuDao.deletePermission(2,2);*/
    }

    @Test
    void testPermissionManager() {
//        权限列表
        /*System.out.println(permissionDao.selectList(new QueryWrapper<>()));*/

//        添加权限
        /*Permission permission = new Permission();
        permission.setName("新增员工");
        permission.setUrl("/saveEmployee");
        permissionDao.insert(permission);*/

//编辑权限
        /*permissionDao.deleteById(3);*/

    }

    @Test
    void testUserService() {
//        查询所有用户
        /*System.out.println(userService.getUsers());*/

//        根据id查询
        /*System.out.println(userService.getUserById(2));*/

//        新增用户
        /*User user = new User();
        user.setName("笑话");
        user.setPassword("751123");
        userService.saveUser(user);*/

//        更新密码
        /*UserDTO userDTO = new UserDTO();
        userDTO.setId(8);
        userDTO.setPassword("123456");
        userService.updatePassword(userDTO);*/

//        删除用户
        /*userService.deleteUserById(8);*/

//        设置角色
        /*UserRoleDTO urd = new UserRoleDTO();
        urd.setUid(2);
        urd.setRid(2);
        userService.setRole(urd);*/
    }

    @Test
    void testRoleService() {
//        System.out.println(roleService.getMenus("员工"));
    }

    @Test
    void testAny() {
        UserRoleDTO urd = new UserRoleDTO();
        urd.setUid(7);
        urd.setRid(2);
       userService.deleteRole(urd);
    }

}
