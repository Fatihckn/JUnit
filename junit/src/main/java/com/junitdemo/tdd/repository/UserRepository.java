package com.junitdemo.tdd.repository;

import com.junitdemo.tdd.model.User;

public interface UserRepository {

    boolean save(User user);
}
