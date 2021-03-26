package com.gqz.mm.controller;

import com.gqz.mm.constants.Constants;
import com.gqz.mm.entity.PageResult;
import com.gqz.mm.entity.QueryPageBean;
import com.gqz.mm.entity.Result;
import com.gqz.mm.pojo.Course;
import com.gqz.mm.pojo.User;
import com.gqz.mm.service.CourseService;
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
public class CourseController {
    /**
     *<p>描述: 学科管理的方法<p>
     *@method add
     *@param request, response+
     *@return void
     *@author gqz20
     *@CreateDate 2021/3/11 11:43
     */
    private CourseService courseService = new CourseService();
    @RequestMapping("/course/add")
    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //那请求参数

        try {
            Course course = JsonUtils.parseJSON2Object(request, Course.class);
            String createDate = DateUtils.parseDate2String(new Date());
            course.setCreateDate(createDate);
            //拿到用户id
            User user = (User)request.getSession().getAttribute(Constants.LONGIN_USER);
            course.setUserId(user.getId());
            course.setOrderNo(2);
            //调用service方法

            courseService.add(course);
            //响应结果
            JsonUtils.printResult(response,new Result(true,"添加成功"));
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtils.printResult(response,new Result(false,"添加失败"));
        }
    }
    @RequestMapping("/course/findByPage")
    public void findByPage(HttpServletRequest request,HttpServletResponse response) throws IOException {
        try {
            QueryPageBean queryPageBean = JsonUtils.parseJSON2Object(request, QueryPageBean.class);
            PageResult pageResult =courseService.findByPage(queryPageBean);
            JsonUtils.printResult(response,new Result(true,"查询成功",pageResult));
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtils.printResult(response,new Result(false,"查询失败",null));
        }
    }
    @RequestMapping("/course/update")
    public void update(HttpServletRequest request,HttpServletResponse response) throws IOException {
        try {
            Course course = JsonUtils.parseJSON2Object(request, Course.class);
            courseService.update(course);
            //success
            JsonUtils.printResult(response,new Result(true,"修改成功",null));
        } catch (Exception e) {
            e.printStackTrace();
            //false
            JsonUtils.printResult(response,new Result(false,"修改失败",null));
        }

    }
    @RequestMapping("/course/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 获取请求参数id
            Integer id = Integer.valueOf(request.getParameter("id"));
            //2. 调用业务层的方法，根据id删除学科
            courseService.deleteById(id);
            //删除成功
            JsonUtils.printResult(response,new Result(true,"删除学科成功"));
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtils.printResult(response,new Result(false,e.getMessage()));
        }
    }
}
