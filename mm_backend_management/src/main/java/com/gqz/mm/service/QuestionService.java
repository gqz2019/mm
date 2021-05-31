package com.gqz.mm.service;

import com.gqz.mm.entity.PageResult;
import com.gqz.mm.entity.QueryPageBean;
import com.gqz.mm.pojo.Question;

import java.io.IOException;

/**
 * <p></p>
 *
 * @author gqz20
 * @create 2021-05-31 21:30
 **/
public interface QuestionService {
    PageResult findBasicByPage(QueryPageBean queryPageBean) throws IOException;

    void add(Question question);
}
