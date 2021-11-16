package com.dsi.ecommerce.service.impl;

import com.dsi.ecommerce.dao.UserDao;
import com.dsi.ecommerce.model.User;
import com.dsi.ecommerce.service.MyUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found."));
        return new MyUserDetail(user);
    }
}
