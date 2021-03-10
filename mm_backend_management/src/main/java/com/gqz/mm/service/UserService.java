package com.gqz.mm.service;

import com.gqz.mm.dao.UserDao;
import com.gqz.mm.pojo.User;
import com.gqz.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;



public class UserService {
    public User login(User user) throws Exception {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        UserDao dao = sqlSession.getMapper(UserDao.class);
        User loginUser = dao.findByUserName(user.getUsername());

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
