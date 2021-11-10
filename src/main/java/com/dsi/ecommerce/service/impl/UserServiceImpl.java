package com.dsi.ecommerce.service.impl;

import com.dsi.ecommerce.dao.UserDao;
import com.dsi.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao ;

}
