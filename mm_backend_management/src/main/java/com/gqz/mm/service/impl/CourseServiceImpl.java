package com.gqz.mm.service.impl;

import com.gqz.mm.dao.CatalogDao;
import com.gqz.mm.dao.CourseDao;
import com.gqz.mm.dao.QuestionDao;
import com.gqz.mm.dao.TagDao;
import com.gqz.mm.entity.PageResult;
import com.gqz.mm.entity.QueryPageBean;
import com.gqz.mm.pojo.Course;
import com.gqz.mm.service.CourseService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author gqz20
 */
@Service
public class CourseServiceImpl implements CourseService {
    /**
     * <p>描述: 添加学科的方法<p>
     *
     * @method add
     * @param course
     * @return void
     * @author gqz20
     * @CreateDate 2021/3/19 19:32
     */
    @Autowired
    private CourseDao courseDao;

    @Autowired
    private CatalogDao catalogDao;

    @Autowired
    private TagDao tagDao;

    @Autowired
    private QuestionDao questionDao;

    @Override
    public void add(Course course) throws Exception {

        courseDao.add(course);

    }

    /**
     * <p>描述: 分页查询<p>
     *
     * @param queryPageBean
     * @return com.gqz.mm.entity.PageResult
     * @method findByPage
     * @author gqz20
     * @createDate 2021/3/19 20:08
     */
    @Override
    public PageResult findByPage(QueryPageBean queryPageBean) {

        Long totalCount = courseDao.findTotalCount(queryPageBean);
        List<Course> courseList = courseDao.findPageList(queryPageBean);
        return new PageResult(totalCount, courseList);
    }

    @Override
    public void update(Course course) throws Exception {

        courseDao.update(course);

    }

    @Override
    public void deleteById(Integer id) throws Exception {
        //根据courseId到t_catalog表中查询数据条数
        Long catalogCount = catalogDao.findCountByCourseId(id);
        if (catalogCount > 0) {
            //有关联的二级目录，不能删除
            throw new RuntimeException("有关联的二级目录，不能删除");
        }

        //2. 判断当前要删除的学科是否有关联的标签
        Long tagCount = tagDao.findCountByCourseId(id);
        if (tagCount > 0) {
            //有关联的标签，不能删除
            throw new RuntimeException("有关联的标签，不能删除");
        }

        //3. 判断当前要删除的学科是否有关联的题目
        Long questionCount = questionDao.findCountByCourseId(id);
        if (questionCount > 0) {
            //有关联的题目，不能删除
            throw new RuntimeException("有关联的题目，不能删除");
        }

        //4. 如果上述三个关联都没有，则调用dao层的方法进行根据id删除
        courseDao.deleteById(id);

    }

    @Override
    public List<Course> findAll(String status) {
//        CatalogDao catalogDao = sqlSession.getMapper(CatalogDao.class);

        List<Course> courseList = courseDao.findAll(status);
//        for (Course course : courseList) {
//            List<Catalog> catalogList=catalogDao.findCatalogListByCourseId(course.getId());
//            course.setCatalogList(catalogList);
//        }
        return courseList;
    }
}