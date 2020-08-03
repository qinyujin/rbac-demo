package com.boss.rbacdemo.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :覃玉锦
 * @create :2020-08-03 21:01:00
 * 封装常用信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyToken {
    public static final String AUTHORIZATION = "authorization";

    private int num;

    private int role;
}
