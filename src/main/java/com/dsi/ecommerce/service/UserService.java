package com.dsi.ecommerce.service;


import com.dsi.ecommerce.exception.NoUserFound;
import com.dsi.ecommerce.exception.UserAlreadyExists;
import com.dsi.ecommerce.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public User createNewUser(User user) throws UserAlreadyExists;

    public List<User> getAllUsers() throws NoUserFound;

    public void deleteUser(Long userId);

    public User getUserById(Long userId);

    public User getUserFromMyUserDetail(MyUserDetail userDetail );

    public User updateUser(Long userId, User user);

}
