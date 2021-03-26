package com.gqz.mm.service;

import com.gqz.mm.dao.CatalogDao;
import com.gqz.mm.dao.CourseDao;
import com.gqz.mm.dao.QuestionDao;
import com.gqz.mm.dao.TagDao;
import com.gqz.mm.entity.PageResult;
import com.gqz.mm.entity.QueryPageBean;
import com.gqz.mm.pojo.Course;
import com.gqz.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
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
        sqlSession.commit();
        List<Course> courseList =mapper.findPageList(queryPageBean);
        return new PageResult(totalCount,courseList);
    }

    public void update(Course course) throws Exception {

        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        CourseDao mapper = sqlSession.getMapper(CourseDao.class);
        mapper.update(course);
        sqlSession.commit();
    }
    public void deleteById(Integer id) throws Exception {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        //1. 判断当前要删除的学科是否有关联的二级目录
        CatalogDao catalogDao = sqlSession.getMapper(CatalogDao.class);
        //根据courseId到t_catalog表中查询数据条数
        Long catalogCount = catalogDao.findCountByCourseId(id);
        if (catalogCount > 0) {
            //有关联的二级目录，不能删除
            throw new RuntimeException("有关联的二级目录，不能删除");
        }

        //2. 判断当前要删除的学科是否有关联的标签
        TagDao tagDao = sqlSession.getMapper(TagDao.class);
        Long tagCount = tagDao.findCountByCourseId(id);
        if (tagCount > 0) {
            //有关联的标签，不能删除
            throw new RuntimeException("有关联的标签，不能删除");
        }

        //3. 判断当前要删除的学科是否有关联的题目
        QuestionDao questionDao = sqlSession.getMapper(QuestionDao.class);
        Long questionCount = questionDao.findCountByCourseId(id);
        if (questionCount > 0) {
            //有关联的题目，不能删除
            throw new RuntimeException("有关联的题目，不能删除");
        }

        //4. 如果上述三个关联都没有，则调用dao层的方法进行根据id删除
        CourseDao courseDao = sqlSession.getMapper(CourseDao.class);
        courseDao.deleteById(id);

        SqlSessionFactoryUtils.commitAndClose(sqlSession);
    }
}