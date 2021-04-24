package com.gqz.wx.dao;

import com.gqz.wx.pojo.Course;

import java.util.List;

/**
 * 包名:com.itheima.mm.dao
 *
 * @author Leevi
 * 日期2020-08-05  12:11
 */
public interface CourseDao {

    List<Course> findAll();
}
