package com.junitdemo.tdd.exception;

public class EmailNotificationServiceException extends RuntimeException {
    public EmailNotificationServiceException(String message) {
        super(message);
    }
}
