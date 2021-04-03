package com.gqz.mm.dao;

import com.gqz.mm.entity.QueryPageBean;
import com.gqz.mm.pojo.Question;

import java.util.List;

/**
 * @program: mm
 * @description:
 * @author: gqz20
 * @create: 2021-03-26 22:28
 **/
public interface QuestionDao {
    Long findCountByCourseId(Integer id) ;

    Long getTotalBasicCount(QueryPageBean queryPageBean);

    List<Question> findBasicQuesionList(QueryPageBean queryPageBean);
}
