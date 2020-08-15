package com.boss.mvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :覃玉锦
 * @create :2020-08-05 09:13:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private int id;

    private String name;

    private String url;

    private int parentId;
}
