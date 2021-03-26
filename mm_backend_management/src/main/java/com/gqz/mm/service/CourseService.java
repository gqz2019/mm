package com.gqz.mm.service;

import com.gqz.mm.dao.CourseDao;
import com.gqz.mm.entity.PageResult;
import com.gqz.mm.entity.QueryPageBean;
import com.gqz.mm.pojo.Course;
import com.gqz.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author gqz20
 */
public class CourseService {
    /**
     *<p>描述: 添加学科的方法<p>
     *@method add
     *@param course
     *@return void
     *@author gqz20
     *@CreateDate 2021/3/19 19:32
     */
    public void add(Course course) throws Exception {

        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        CourseDao dao = sqlSession.getMapper(CourseDao.class);
        dao.add(course);
        sqlSession.commit();
    }
    /**
     *<p>描述: 分页查询<p>
     *@method findByPage
     *@param queryPageBean
     *@return com.gqz.mm.entity.PageResult
     *@author gqz20
     *@createDate 2021/3/19 20:08
     */
    public PageResult findByPage(QueryPageBean queryPageBean) throws Exception {
        
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        CourseDao mapper = sqlSession.getMapper(CourseDao.class);
        Long totalCount=mapper.findTotalCount(queryPageBean);
        List<Course> courseList =mapper.findPageList(queryPageBean);
        return new PageResult(totalCount,courseList);
    }
}