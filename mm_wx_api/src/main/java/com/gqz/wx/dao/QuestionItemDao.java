package com.gqz.wx.dao;

import com.gqz.wx.pojo.QuestionItem;


import java.util.List;

/**
 * 包名:com.itheima.mm.dao
 *
 * @author Leevi
 * 日期2020-08-06  14:53
 */
public interface QuestionItemDao {
    List<QuestionItem> findItemListByQuestionId(Integer questionId);
}
