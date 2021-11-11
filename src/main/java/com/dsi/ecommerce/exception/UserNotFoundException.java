package com.dsi.ecommerce.exception;

import com.dsi.ecommerce.model.User;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super(("No User found."));
    }
    public UserNotFoundException(long userId) {
        super("User not found with id: "+ userId);
    }

    public UserNotFoundException(String username) {
        super("User not found with username: " + username);
    }
}
