package com.devjobs.application.usecase;

import com.devjobs.domain.model.User;
import com.devjobs.domain.ports.output.UserRepository;

public class RegisterUserService {

    private final UserRepository userRepository;

    public RegisterUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) {
        return userRepository.save(user);
    }
}
