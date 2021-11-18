package com.dsi.ecommerce.exception;

import com.dsi.ecommerce.model.User;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(User user) {
        super("Username already exists: " + user.getUsername());
    }
}
