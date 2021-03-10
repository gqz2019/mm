package com.gqz.mm.dao;

import com.gqz.mm.pojo.User;

public interface UserDao {

    User findByUserName(String username);
}
