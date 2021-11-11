package com.dsi.ecommerce.service;


import com.dsi.ecommerce.exception.UserAlreadyExistsException;
import com.dsi.ecommerce.exception.UserNotFoundException;
import com.dsi.ecommerce.model.User;

import java.util.List;

public interface UserService {
    public User createNewUser(User user) throws UserAlreadyExistsException;

    public List<User> getAllUsers() throws UserNotFoundException;

    public void deleteUserByUsername(String username) throws UserNotFoundException;

    public User getUserById(Long userId) throws UserNotFoundException;

    public User getUserByUsername(String username) throws UserNotFoundException;

    public User getUserFromMyUserDetail(MyUserDetail userDetail ) throws UserNotFoundException;

    public User updateUser(String username, User user) throws UserNotFoundException;

}
