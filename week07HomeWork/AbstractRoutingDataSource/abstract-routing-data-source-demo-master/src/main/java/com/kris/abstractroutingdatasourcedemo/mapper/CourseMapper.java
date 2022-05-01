package com.kris.abstractroutingdatasourcedemo.mapper;

import com.kris.abstractroutingdatasourcedemo.entity.Course;

import java.util.List;

/**
 * @author suncheng
 */
public interface CourseMapper {

    List<Course> findAll();
}
