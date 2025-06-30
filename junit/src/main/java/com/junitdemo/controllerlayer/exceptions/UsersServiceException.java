package com.junitdemo.controllerlayer.exceptions;

public class UsersServiceException extends RuntimeException{
    public UsersServiceException(String message)
    {
        super(message);
    }
}