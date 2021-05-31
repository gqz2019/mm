package com.gqz.mm.service.impl;

import com.gqz.mm.dao.UserDao;
import com.gqz.mm.pojo.User;
import com.gqz.mm.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author gqz20
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User login(User user) throws Exception {
        User loginUser = userDao.findByUserName(user.getUsername());
        //判断
        if (loginUser != null) {
            //用户存在
            if (user.getPassword().equals(loginUser.getPassword())) {
                return loginUser;
            } else {
                throw new RuntimeException("密码错误");
            }

        } else {
            //用户不存在
            throw new RuntimeException("用户名错误");
        }

    }
}
