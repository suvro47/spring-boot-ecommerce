package com.dsi.ecommerce.service;


import com.dsi.ecommerce.exception.UserAlreadyExists;
import com.dsi.ecommerce.exception.UserNotFound;
import com.dsi.ecommerce.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    public User createNewUser(User user) throws UserAlreadyExists;

    public List<User> getAllUsers() throws UserNotFound;

    public void deleteUserByUsername(String username) throws UserNotFound;

    public User getUserById(Long userId) throws UserNotFound;

    public User getUserByUsername(String username) throws UserNotFound;

    public User getUserFromMyUserDetail(MyUserDetail userDetail ) throws UserNotFound;

    public User updateUser(String username, User user) throws UserNotFound;

}
