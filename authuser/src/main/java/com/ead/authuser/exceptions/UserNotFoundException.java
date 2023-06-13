package com.ead.authuser.exceptions;

public class UserNotFoundException extends RuntimeException {
	
    public UserNotFoundException(String msg) {
        super(msg);
    }

}
