package com.gqz.mm.dao;

import com.gqz.mm.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    User findByUserName(String username);
}
