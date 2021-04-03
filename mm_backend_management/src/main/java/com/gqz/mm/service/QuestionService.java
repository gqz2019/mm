package com.gqz.mm.service;

import com.gqz.mm.dao.QuestionDao;
import com.gqz.mm.entity.PageResult;
import com.gqz.mm.entity.QueryPageBean;
import com.gqz.mm.pojo.Question;
import com.gqz.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

/**
 * @program: mm
 * @description:
 * @author: gqz20
 * @create: 2021-04-01 14:47
 **/
public class QuestionService {
    public PageResult findBasicByPage(QueryPageBean queryPageBean) throws IOException {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        QuestionDao questionDao = sqlSession.getMapper(QuestionDao.class);

        Long totalBasicCount=questionDao.getTotalBasicCount(queryPageBean);
        List<Question> questionList=questionDao.findBasicQuesionList(queryPageBean);

        return new PageResult(totalBasicCount,questionList);
    }
}
