package com.dsi.ecommerce.service.impl;

import com.dsi.ecommerce.dao.UserDao;
import com.dsi.ecommerce.exception.UserAlreadyExistsException;
import com.dsi.ecommerce.exception.UserNotFoundException;
import com.dsi.ecommerce.model.User;
import com.dsi.ecommerce.service.MyUserDetail;
import com.dsi.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao ;

    @Override
    public User createNewUser(User user) throws UserAlreadyExistsException {
        if (userDao.findByUsername(user.getUsername()) != null){
            throw new UserAlreadyExistsException(user);
        }
        user.setActive(true);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userDao.save(user);
    }

    @Override
    public List<User> getAllUsers() throws UserNotFoundException {
        List<User> users = userDao.findAll();
        if (users.isEmpty()){
            throw new UserNotFoundException();
        }
        return users;
    }

    @Override
    public void deleteUserByUsername(String username) throws UserNotFoundException {
        userDao.delete(getUserByUsername(username));
    }

    @Override
    public User getUserById(Long userId) throws UserNotFoundException {
        User user = userDao.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        return user;
    }

    @Override
    public User getUserByUsername(String username) throws UserNotFoundException {
        User user = userDao.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username + " not found."));
        if (user == null){
            throw new UserNotFoundException(username);
        }

        return user;
    }

    @Override
    public User getUserFromMyUserDetail(MyUserDetail userDetail) throws UserNotFoundException {
        return getUserById(userDetail.getId());
    }

    @Override
    public User updateUser(String username, User userData) throws UserNotFoundException {
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
