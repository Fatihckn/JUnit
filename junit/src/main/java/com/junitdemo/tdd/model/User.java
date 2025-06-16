package com.junitdemo.tdd.model;

public class User {
    String username;
    String email;
    String password;

    public User(String username, String password, String email) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
