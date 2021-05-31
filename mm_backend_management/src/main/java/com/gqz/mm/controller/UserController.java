package com.gqz.mm.controller;

import com.gqz.mm.constants.Constants;
import com.gqz.mm.entity.Result;
import com.gqz.mm.pojo.User;
import com.gqz.mm.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author gqz20
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("login")
    public Result login(@RequestBody User user, HttpServletRequest request) throws IOException {
        Result result;
        try {
            User loginUser = userService.login(user);
            //将user状态存入session
            request.getSession().setAttribute(Constants.LONGIN_USER, loginUser);
            result = new Result(true, "登陆成功", loginUser);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,e.getMessage(),null);
        }

    }
    @RequestMapping("logout")
    public Result logout(HttpServletRequest request) throws IOException {
        request.getSession().invalidate();
        return new Result(true,"退出成功",null);
    }
}
