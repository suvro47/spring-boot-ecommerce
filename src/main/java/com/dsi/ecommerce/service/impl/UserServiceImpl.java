package com.dsi.ecommerce.service.impl;

import com.dsi.ecommerce.dao.UserDao;
import com.dsi.ecommerce.exception.NoUserFound;
import com.dsi.ecommerce.exception.UserAlreadyExists;
import com.dsi.ecommerce.exception.UserNotFound;
import com.dsi.ecommerce.model.User;
import com.dsi.ecommerce.service.MyUserDetail;
import com.dsi.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao ;

    @Override
    public User createNewUser(User user) throws UserAlreadyExists{
        if (userDao.findByUsername(user.getUsername()) != null){
            throw new UserAlreadyExists(user);
        }
        user.setActive(true);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userDao.save(user);
    }

    @Override
    public List<User> getAllUsers() throws NoUserFound{
        List<User> users = userDao.findAll();
        if (users.isEmpty()){
            throw new NoUserFound();
        }
        return users;
    }

    @Override
    public void deleteUser(Long userId) {
        userDao.deleteById(userId);
    }

    @Override
    public User getUserById(Long userId) {
        User user = userDao.findById(userId).orElseThrow(() -> new UserNotFound(userId));

        return user;
    }

    @Override
    public User getUserFromMyUserDetail(MyUserDetail userDetail) {
        return getUserById(userDetail.getId());
    }

    @Override
    public User updateUser(Long userId, User user) {
        return null;
    }
}
