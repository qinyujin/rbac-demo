package com.boss.rbacdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author :覃玉锦
 * @create :2020-08-03 18:07:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private int num;

    private String name;

    private String password;

    private int role;
}
