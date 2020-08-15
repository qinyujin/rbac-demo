package com.boss.mvc.entity.dto;

import lombok.Data;

/**
 * @author :覃玉锦
 * @create :2020-08-05 12:39:00
 * 注，int修改为integer包装类要养成习惯，因为pojo类规范
 */
@Data
public class MenuPermissionDTO {
    private Integer mid;

    private Integer pid;
}
