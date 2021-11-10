package com.dsi.ecommerce.exception;

public class NoUserFound extends RuntimeException{
    public NoUserFound() {
        super("No user found.");
    }
}
