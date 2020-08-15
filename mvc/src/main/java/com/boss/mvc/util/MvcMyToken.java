package com.boss.mvc.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :覃玉锦
 * @create :2020-08-15 22:32:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MvcMyToken {
    public static final String AUTHORIZATION = "authorization";

    private int uid;

    private int role;
}
