package com.gqz.mm.controller;

import com.gqz.mm.entity.PageResult;
import com.gqz.mm.entity.QueryPageBean;
import com.gqz.mm.entity.Result;
import com.gqz.mm.service.QuestionService;
import com.gqz.mm.utils.JsonUtils;
import com.itheima.framework.anno.Controller;
import com.itheima.framework.anno.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: mm
 * @description: 题库
 * @author: gqz20
 * @create: 2021-04-01 14:40
 **/
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
}
