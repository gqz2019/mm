package com.gqz.mm.service;

import com.gqz.mm.dao.CourseDao;
import com.gqz.mm.pojo.Course;
import com.gqz.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

/**
 * @author gqz20
 */
public class CourseService {

    public void add(Course course) throws Exception {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        CourseDao dao = sqlSession.getMapper(CourseDao.class);
        dao.add(course);
        sqlSession.commit();
    }
}
