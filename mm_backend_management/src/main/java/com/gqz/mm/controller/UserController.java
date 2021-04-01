package com.gqz.mm.controller;

import com.gqz.mm.constants.Constants;
import com.gqz.mm.entity.Result;
import com.gqz.mm.pojo.User;
import com.gqz.mm.service.UserService;
import com.gqz.mm.utils.JsonUtils;
import com.itheima.framework.anno.Controller;
import com.itheima.framework.anno.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author gqz20
 */
@Controller
public class UserController {
    private UserService userService = new UserService();

    @RequestMapping("/user/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Result result;
        try {
            User user = JsonUtils.parseJSON2Object(request, User.class);
            User loginUser = userService.login(user);
            //将user状态存入session
            request.getSession().setAttribute(Constants.LONGIN_USER, loginUser);
            result = new Result(true, "登陆成功", loginUser);
            JsonUtils.printResult(response, result);
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtils.printResult(response, new Result(false, e.getMessage(), null));
        }

    }
    @RequestMapping("/user/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        JsonUtils.printResult(response,new Result(true,"退出成功",null));
    }
}
