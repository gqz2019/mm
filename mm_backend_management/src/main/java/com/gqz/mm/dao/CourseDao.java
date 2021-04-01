package com.gqz.mm.dao;

import com.gqz.mm.entity.QueryPageBean;
import com.gqz.mm.pojo.Course;

import java.util.List;

/**
 * @program: mm
 * @description: 学科添加的dao
 * @author: gqz20
 * @create: 2021-03-18 14:07
 **/
public interface CourseDao {
    void add(Course course);

    Long findTotalCount(QueryPageBean queryPageBean);

    List<Course> findPageList(QueryPageBean queryPageBean);

    void update(Course course);

    void deleteById(Integer id);

    List<Course> findAll(String status);
}
