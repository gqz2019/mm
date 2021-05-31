package com.gqz.wx.dao;

import com.gqz.wx.pojo.QuestionItem;


import java.util.List;


/**
 * @author gqz20
 */
public interface QuestionItemDao {
    List<QuestionItem> findItemListByQuestionId(Integer questionId);
}
