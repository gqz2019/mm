package com.gqz.mm.service.impl;

import com.gqz.mm.dao.QuestionDao;
import com.gqz.mm.dao.QuestionItemDao;
import com.gqz.mm.entity.PageResult;
import com.gqz.mm.entity.QueryPageBean;
import com.gqz.mm.pojo.Question;
import com.gqz.mm.pojo.QuestionItem;
import com.gqz.mm.pojo.Tag;
import com.gqz.mm.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @program: mm
 * @description:
 * @author: gqz20
 * @create: 2021-04-01 14:47
 **/
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private QuestionItemDao questionItemDao;
    @Override
    public PageResult findBasicByPage(QueryPageBean queryPageBean) throws IOException {
        Long totalBasicCount = questionDao.getTotalBasicCount(queryPageBean);
        List<Question> questionList = questionDao.findBasicQuesionList(queryPageBean);

        return new PageResult(totalBasicCount, questionList);
    }

    @Override
    public void add(Question question) {


        //1. 将t_question表相关的数据存储到t_question表
        questionDao.add(question);
        //2. 判断选项的集合是否为空，如果不为空将题目的选项信息，存储到t_question_item表

        List<QuestionItem> questionItemList = question.getQuestionItemList();
        if (questionItemList != null && questionItemList.size() > 0) {
            for (QuestionItem questionItem : questionItemList) {
                //手动设置该选项所属的question的id
                questionItem.setQuestionId(question.getId());
                //调用dao层的方法，存储每一个题目的选项信息

                questionItemDao.add(questionItem);
            }
        }
        //进行标签关联
        List<Tag> tags = question.getTagList();
        if (tags != null && tags.size()>0) {
            for (Tag tag : tags) {
                HashMap<String, Integer> map = new HashMap<>();
                map.put("questionId",question.getId());
                map.put("tagId",tag.getId());
                questionDao.addQuestionTag(map);
            }
        }
    }
}
