package com.junitdemo.tdd.service;

import com.junitdemo.tdd.model.User;

public interface UserService {
    User createUser(String username, String password, String email, String passwordConfirm);
}
