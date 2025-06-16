package com.junitdemo.tdd.exception;

public class UserServiceException extends RuntimeException {
    public UserServiceException(String message){
        super(message);
    }
}
