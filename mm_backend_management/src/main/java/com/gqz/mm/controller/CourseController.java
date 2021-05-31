package com.gqz.mm.controller;

import com.gqz.mm.constants.Constants;
import com.gqz.mm.entity.PageResult;
import com.gqz.mm.entity.QueryPageBean;
import com.gqz.mm.entity.Result;
import com.gqz.mm.pojo.Course;
import com.gqz.mm.pojo.User;
import com.gqz.mm.service.impl.CourseServiceImpl;
import com.gqz.mm.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author gqz20
 */
@RestController
public class CourseController {
    /**
     * <p>描述: 学科管理的方法<p>
     *
     * @method add
     * @param request, response+
     * @return void
     * @author gqz20
     * @CreateDate 2021/3/11 11:43
     */
    @Autowired
    private CourseServiceImpl courseService;

    @RequestMapping("/course/add")
    public Result add(@RequestBody Course course, HttpServletRequest request) throws IOException {
        //那请求参数

        try {
            String createDate = DateUtils.parseDate2String(new Date());
            course.setCreateDate(createDate);
            //拿到用户id
            User user = (User) request.getSession().getAttribute(Constants.LONGIN_USER);
            course.setUserId(user.getId());
//            course.setUserId((Integer) request.getSession().getAttribute(Constants.LONGIN_USER));
            course.setOrderNo(2);
            //调用service方法

            courseService.add(course);
            //响应结果
            return new Result(true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "添加失败");
        }
    }

    @RequestMapping("/course/findByPage")
    public Result findByPage(@RequestBody QueryPageBean queryPageBean) throws IOException {
        try {
            PageResult pageResult = courseService.findByPage(queryPageBean);
            return new Result(true, "查询成功", pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "查询失败", null);
        }
    }

    @RequestMapping("/course/update")
    public Result update(@RequestBody Course course) throws IOException {
        try {
            courseService.update(course);
            //success
            return new Result(true, "修改成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            //false
            return new Result(false, "修改失败", null);
        }
    }

    @RequestMapping("/course/delete")
    public Result delete(@RequestParam("id") Integer id) throws IOException {
        try {
            //1. 获取请求参数id
            //2. 调用业务层的方法，根据id删除学科
            courseService.deleteById(id);
            //删除成功
            return new Result(true, "删除学科成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, e.getMessage());
        }
    }

    @RequestMapping("/course/findAll")
    public Result findAll(String status) throws IOException {
        try {
            //2. 调用业务层的方法，根据id删除学科
            List<Course> courseList = courseService.findAll(status);
            //删除成功
            return new Result(true, "查村所有学科成功", courseList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, e.getMessage());
        }
    }
}
