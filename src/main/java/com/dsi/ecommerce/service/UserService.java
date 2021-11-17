package com.dsi.ecommerce.service;


import com.dsi.ecommerce.exception.UserAlreadyExistsException;
import com.dsi.ecommerce.exception.UserNotFoundException;
import com.dsi.ecommerce.model.MyUserDetail;
import com.dsi.ecommerce.model.User;

import java.util.List;

public interface UserService {
     User createNewUser(User user) throws UserAlreadyExistsException;

     List<User> getAllUsers() throws UserNotFoundException;

     void deleteUserByUsername(String username) throws UserNotFoundException;

     User getUserById(Long userId) throws UserNotFoundException;

     User getUserByUsername(String username) throws UserNotFoundException;

     User getUserFromMyUserDetail(MyUserDetail userDetail ) throws UserNotFoundException;

     User updateUser(String username, User user) throws UserNotFoundException;

}
