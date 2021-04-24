package com.gqz.wx.service;

import com.gqz.wx.dao.CourseDao;
import com.gqz.wx.pojo.Course;

import com.gqz.wx.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * 学科的业务层
 * @author gqz20
 */
public class CourseService {

    public List<Course> findAll() throws Exception {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        CourseDao courseDao = sqlSession.getMapper(CourseDao.class);
        List<Course> courseList = courseDao.findAll();

        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        return courseList;
    }
}
