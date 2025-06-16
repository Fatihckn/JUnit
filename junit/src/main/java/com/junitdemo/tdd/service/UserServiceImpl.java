package com.junitdemo.tdd.service;

import com.junitdemo.tdd.exception.UserServiceException;
import com.junitdemo.tdd.model.User;
import com.junitdemo.tdd.repository.UserRepository;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final EmailVerificationService emailVerificationService;

    public UserServiceImpl(UserRepository userRepository,
                           EmailVerificationService emailVerificationService) {
        this.userRepository = userRepository;
        this.emailVerificationService = emailVerificationService;
    }

    @Override
    public User createUser(String username, String password,
                           String email, String passwordConfirm) {
        if(username == null || username.trim().isEmpty()){
            throw new IllegalArgumentException("Username is empty");
        }

        User user = new User(username, password, email);

        boolean isUserCreated;

        try {
            isUserCreated = userRepository.save(user);
        }catch(RuntimeException e){
            throw new UserServiceException(e.getMessage());
        }

        try{
            emailVerificationService.scheduleEmailConfirmation(user);
        } catch (RuntimeException e){
            throw new UserServiceException(e.getMessage());
        }


        if(!isUserCreated){
            throw new UserServiceException("Could not create User");
        }

        return user;
    }
}
