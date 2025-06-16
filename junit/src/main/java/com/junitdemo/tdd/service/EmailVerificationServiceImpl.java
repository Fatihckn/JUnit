package com.junitdemo.tdd.service;

import com.junitdemo.tdd.model.User;

public class EmailVerificationServiceImpl implements EmailVerificationService {
    @Override
    public void scheduleEmailConfirmation(User user){
        // Put user details into email queue
    }
}
