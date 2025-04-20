package com.devjobs.application.usecase;

import com.devjobs.domain.exception.UserNotFoundException;
import com.devjobs.domain.model.User;
import com.devjobs.domain.ports.output.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegisterUserService {

    private final UserRepository userRepository;

    public RegisterUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) {
        return userRepository.save(user);
    }

    public User findUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    }
}
