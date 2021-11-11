package com.dsi.ecommerce.exception;

import com.dsi.ecommerce.model.User;

public class UserAlreadyExists extends RuntimeException{
    public UserAlreadyExists(User user) {
        super("Username already exists: " + user.getUsername());
    }
}
