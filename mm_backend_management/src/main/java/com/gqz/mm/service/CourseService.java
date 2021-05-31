package com.gqz.mm.service;

import com.gqz.mm.entity.PageResult;
import com.gqz.mm.entity.QueryPageBean;
import com.gqz.mm.pojo.Course;

import java.util.List;

/**
 * <p></p>
 *
 * @author gqz20
 * @create 2021-05-31 21:31
 **/
public interface CourseService {
    void add(Course course) throws Exception;

    PageResult findByPage(QueryPageBean queryPageBean);

    void update(Course course) throws Exception;

    void deleteById(Integer id) throws Exception;

    List<Course> findAll(String status);
}
