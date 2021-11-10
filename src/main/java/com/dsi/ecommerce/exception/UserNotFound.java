package com.dsi.ecommerce.exception;

import com.dsi.ecommerce.model.User;

public class UserNotFound extends RuntimeException{
    public UserNotFound(User user) {
        super("User not found "+ user);
    }
}
