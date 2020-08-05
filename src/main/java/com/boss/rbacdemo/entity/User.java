package com.boss.rbacdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(value = "password")
public class User implements Serializable {
    private int id;

    private String name;

    private String password;

}
