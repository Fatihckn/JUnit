package com.junitdemo.integration.junit.exceptions;

public class UsersServiceException extends RuntimeException{
    public UsersServiceException(String message)
    {
        super(message);
    }
}