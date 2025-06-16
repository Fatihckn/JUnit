package com.junitdemo.tdd.repository;

import com.junitdemo.tdd.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {
    Map<String, String> users = new HashMap<>();

    @Override
    public boolean save(User user){

        boolean returnValue = false;

        if(!users.containsKey(user.getUsername())){
            users.put(user.getUsername(), user.getPassword());
            returnValue = true;
        }

        return returnValue;
    }
}
