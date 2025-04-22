package com.devjobs.adapters.in.rest.controller;

import com.devjobs.adapters.in.rest.dto.RegisterUserRequest;
import com.devjobs.adapters.in.rest.dto.RegisterUserResponse;
import com.devjobs.application.usecase.RegisterUserService;
import com.devjobs.domain.model.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<RegisterUserResponse> getUserById(@PathVariable UUID id) {
        User user = registerUserService.findUserById(id);
        RegisterUserResponse response = new RegisterUserResponse(
                user.getId().toString(),
                user.getName(),
                user.getEmail(),
                user.getRole().toString()
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID id) {
        registerUserService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}