package com.gqz.mm.dao;

import com.gqz.mm.entity.QueryPageBean;
import com.gqz.mm.pojo.Question;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @program: mm
 * @description:
 * @author: gqz20
 * @create: 2021-03-26 22:28
 **/
@Repository
public interface QuestionDao {
    Long findCountByCourseId(Integer id) ;

    Long getTotalBasicCount(QueryPageBean queryPageBean);

    List<Question> findBasicQuesionList(QueryPageBean queryPageBean);

    void add(Question question);

    void addQuestionTag(HashMap<String, Integer> map);
}
