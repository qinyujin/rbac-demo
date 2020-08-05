package com.boss.rbacdemo.contoller.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :覃玉锦
 * @create :2020-08-05 21:10:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    private Integer id;

    private String name;

    private String role;
}
