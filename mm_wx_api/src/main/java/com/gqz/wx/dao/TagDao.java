package com.gqz.wx.dao;

import com.gqz.wx.pojo.Tag;


import java.util.List;

/**
 * 包名:com.itheima.mm.dao
 *
 * @author Leevi
 * 日期2020-08-06  14:43
 */
public interface TagDao {
    List<Tag> findTagListByQuestionId(Integer questionId);
}
