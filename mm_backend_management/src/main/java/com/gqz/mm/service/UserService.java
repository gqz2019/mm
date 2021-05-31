package com.gqz.mm.service;

import com.gqz.mm.pojo.User;

/**
 * <p></p>
 *
 * @author gqz20
 * @create 2021-05-31 21:29
 **/
public interface UserService {
    User login(User user) throws Exception;
}
