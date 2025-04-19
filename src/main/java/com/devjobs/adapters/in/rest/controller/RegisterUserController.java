package com.devjobs.adapters.in.rest.controller;

import com.devjobs.adapters.in.rest.dto.RegisterUserRequest;
import com.devjobs.adapters.in.rest.dto.RegisterUserResponse;
import com.devjobs.application.usecase.RegisterUserService;
import com.devjobs.domain.model.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class RegisterUserController {

    private final RegisterUserService registerUserService;

    public RegisterUserController(RegisterUserService registerUserService) {
        this.registerUserService = registerUserService;
    }

    @PostMapping("")
    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody @Valid RegisterUserRequest request) {

        User user = new User(request.name(), request.email(), request.password(), request.role());

        User createdUser = registerUserService.register(user);

        RegisterUserResponse response = new RegisterUserResponse(
                createdUser.getId().toString(),
                createdUser.getName(),
                createdUser.getEmail(),
                createdUser.getRole().toString()
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}