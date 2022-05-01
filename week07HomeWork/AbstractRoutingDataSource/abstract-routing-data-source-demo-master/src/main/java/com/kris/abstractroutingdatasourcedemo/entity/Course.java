package com.kris.abstractroutingdatasourcedemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 课程实体类
 *
 * @author suncheng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    private int id;

    private String name;
}
