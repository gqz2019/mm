package com.gqz.wx.controller;

import com.gqz.wx.entity.Result;
import com.gqz.wx.pojo.Course;
import com.gqz.wx.service.CityService;
import com.gqz.wx.service.CourseService;
import com.gqz.wx.utils.JsonUtils;
import com.itheima.framework.anno.Controller;
import com.itheima.framework.anno.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 包名:com.itheima.mm.controller
 *
 * @author Leevi
 * 日期2020-08-05  10:16
 */
@Controller
public class CommonController {
    private CityService cityService = new CityService();
    private CourseService courseService = new CourseService();
    @RequestMapping(value = "/common/cities")
    public void cities(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 获取请求参数
            Map parameterMap = JsonUtils.parseJSON2Object(request, Map.class);
            //2. 调用业务层的方法，获取到城市信息
            Map resultMap = cityService.findCityList(parameterMap);
            JsonUtils.printResult(response,new Result(true,"获取城市列表成功",resultMap));
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtils.printResult(response,new Result(false,"获取城市列表失败"));
        }
    }

    @RequestMapping("/common/courseList")
    public void courseList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 调用courseService的方法，查询所有学科信息
            List<Course> courseList = courseService.findAll();

            //2. 封装响应数据
            JsonUtils.printResult(response,new Result(true,"获取学科列表成功",courseList));
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtils.printResult(response,new Result(false,"获取学科列表失败"));
        }

    }
}
