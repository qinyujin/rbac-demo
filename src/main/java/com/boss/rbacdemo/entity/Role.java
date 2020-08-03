package com.boss.rbacdemo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author :覃玉锦
 * @create :2020-08-03 20:09:00
 * 默认拥有Admin和User两个角色，当前设计为写死不可改。
 */
@Data
public class Role implements Serializable {
    private int rid;

    private String detail;
}
