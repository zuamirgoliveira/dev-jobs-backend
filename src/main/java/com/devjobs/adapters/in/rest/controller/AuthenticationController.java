package com.devjobs.adapters.in.rest.controller;

import com.devjobs.adapters.in.rest.dto.LoginRequest;
import com.devjobs.adapters.in.rest.dto.LoginResponse;
import com.devjobs.application.usecase.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        authenticationService.authenticate(request.email(), request.password());
        return ResponseEntity.ok(new LoginResponse("Login successful"));
    }
}