package com.ead.authuser.config;


import com.ead.authuser.exceptions.UserConflictException;
import com.ead.authuser.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {
    @ExceptionHandler(value = {UserNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String userNotFoundException (UserNotFoundException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(value = {UserConflictException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public String userConflictException (UserConflictException ex){
        return ex.getMessage();
    }
}
