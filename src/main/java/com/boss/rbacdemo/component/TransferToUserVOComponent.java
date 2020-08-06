package com.boss.rbacdemo.component;

import com.boss.rbacdemo.contoller.vo.UserVO;
import com.boss.rbacdemo.dao.RoleDao;
import com.boss.rbacdemo.entity.Role;
import com.boss.rbacdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author :覃玉锦
 * @create :2020-08-05 21:13:00
 * 把user转换成uservo
 */
@Component
public class TransferToUserVOComponent {
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RequestComponent requestComponent;

    public UserVO transferToVO(User user) {
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setName(user.getName());
        Role role = roleDao.selectById(requestComponent.getRole());
        userVO.setRole(role.getName());
        return userVO;
    }
}
