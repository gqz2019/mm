package com.gqz.mm.dao;

import com.gqz.mm.pojo.QuestionItem;
import org.springframework.stereotype.Repository;

/**
 * @program: mm
 * @description:
 * @author: gqz20
 * @create: 2021-04-04 09:07
 **/
@Repository
public interface QuestionItemDao {

    void add(QuestionItem questionItem);
}
