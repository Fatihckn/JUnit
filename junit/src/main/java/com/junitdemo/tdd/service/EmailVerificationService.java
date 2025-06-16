package com.junitdemo.tdd.service;

import com.junitdemo.tdd.model.User;

public interface EmailVerificationService {
    void scheduleEmailConfirmation(User user);
}
