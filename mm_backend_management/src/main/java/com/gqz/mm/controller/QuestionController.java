package com.gqz.mm.controller;

import com.gqz.mm.constants.Constants;
import com.gqz.mm.entity.PageResult;
import com.gqz.mm.entity.QueryPageBean;
import com.gqz.mm.entity.Result;
import com.gqz.mm.pojo.Question;
import com.gqz.mm.pojo.User;
import com.gqz.mm.service.impl.QuestionServiceImpl;
import com.gqz.mm.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * @author gqz20
 */
@RestController
public class QuestionController {
    @Autowired
    private QuestionServiceImpl questionService;

    @RequestMapping("/question/findBasicByPage")
    public Result findBasicByPage(@RequestBody QueryPageBean queryPageBean) throws IOException {
        try {
            PageResult pageResult = questionService.findBasicByPage(queryPageBean);

            return new Result(true,"查询成功",pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,e.getMessage(),null);
        }

    }
    @RequestMapping("/question/add")
    public Result addQuestion(@RequestBody Question question,HttpServletRequest request) throws IOException {
        try {
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
            return new Result(true,"添加试题成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"添加试题失败");
        }
    }
}
