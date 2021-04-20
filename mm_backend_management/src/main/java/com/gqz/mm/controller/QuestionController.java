package com.gqz.mm.controller;

import com.gqz.mm.constants.Constants;
import com.gqz.mm.entity.PageResult;
import com.gqz.mm.entity.QueryPageBean;
import com.gqz.mm.entity.Result;
import com.gqz.mm.pojo.Question;
import com.gqz.mm.pojo.User;
import com.gqz.mm.service.QuestionService;
import com.gqz.mm.utils.DateUtils;
import com.gqz.mm.utils.JsonUtils;
import com.itheima.framework.anno.Controller;
import com.itheima.framework.anno.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author gqz20
 */
@Controller
public class QuestionController {
    private QuestionService questionService = new QuestionService();

    @RequestMapping("/question/findBasicByPage")
    public void findBasicByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            QueryPageBean queryPageBean = JsonUtils.parseJSON2Object(request, QueryPageBean.class);
            PageResult pageResult = questionService.findBasicByPage(queryPageBean);

            JsonUtils.printResult(response,new Result(true,"查询成功",pageResult));
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtils.printResult(response,new Result(false,e.getMessage(),null));
        }

    }
    @RequestMapping("/question/add")
    public void addQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Question question = JsonUtils.parseJSON2Object(request, Question.class);

            //手动设置缺失的数据: status,reviewStatus,createDate,userId
            question.setStatus(Constants.QUESTION_PRE_PUBLISH);
            question.setReviewStatus(Constants.QUESTION_PRE_REVIEW);
            question.setCreateDate(DateUtils.parseDate2String(new Date()));
//            User user = (User) request.getSession().getAttribute(Constants.LONGIN_USER);
            User user = (User) request.getSession().getAttribute("login_user");
            question.setUserId(user.getId());
//            question.setUserId((Integer) request.getSession().getAttribute(Constants.LONGIN_USER));

            //2. 调用业务层的方法，添加题目信息
            questionService.add(question);
            JsonUtils.printResult(response,new Result(true,"添加试题成功"));
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtils.printResult(response,new Result(false,"添加试题失败"));
        }
    }
}
