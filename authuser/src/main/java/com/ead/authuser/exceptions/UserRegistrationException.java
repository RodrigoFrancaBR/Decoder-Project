package com.ead.authuser.exceptions;

public class UserRegistrationException extends RuntimeException{
    UserRegistrationException(Exception ex){
        super(ex);
    }
}
