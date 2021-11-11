package com.dsi.ecommerce.service.impl;

import com.dsi.ecommerce.dao.UserDao;
import com.dsi.ecommerce.exception.UserAlreadyExists;
import com.dsi.ecommerce.exception.UserNotFound;
import com.dsi.ecommerce.model.User;
import com.dsi.ecommerce.service.MyUserDetail;
import com.dsi.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public List<User> getAllUsers() throws UserNotFound{
        List<User> users = userDao.findAll();
        if (users.isEmpty()){
            throw new UserNotFound();
        }
        return users;
    }

    @Override
    public void deleteUserByUsername(String username) throws UserNotFound {
        userDao.delete(getUserByUsername(username));
    }

    @Override
    public User getUserById(Long userId) throws UserNotFound{
        User user = userDao.findById(userId).orElseThrow(() -> new UserNotFound(userId));

        return user;
    }

    @Override
    public User getUserByUsername(String username) throws UserNotFound {
        User user = userDao.findByUsername(username);
        if (user == null){
            throw new UserNotFound(username);
        }

        return user;
    }

    @Override
    public User getUserFromMyUserDetail(MyUserDetail userDetail) throws UserNotFound {
        return getUserById(userDetail.getId());
    }

    @Override
    public User updateUser(String username, User userData) throws UserNotFound{
        User user = getUserByUsername(username);
        user.setUsername(userData.getUsername());
        user.setEmail(userData.getEmail());
        user.setFirstname(userData.getFirstname());
        user.setLastname(userData.getLastname());
        user.setProfilePic(userData.getProfilePic());
        user.setActive(userData.isActive());
        user.setRole(userData.getRole());

        return userDao.save(user);
    }
}
