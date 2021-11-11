package com.dsi.ecommerce.exception;

import com.dsi.ecommerce.model.User;

public class UserNotFound extends RuntimeException{
    public UserNotFound(){
        super(("No User found."));
    }
    public UserNotFound(long userId) {
        super("User not found with id: "+ userId);
    }
}
