package com.devjobs.adapters.in.rest.dto;

import com.devjobs.domain.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterUserRequest(
        @NotBlank(message = "Name must not be blank")
        String name,

        @Email(message = "Email should be valid")
        @NotBlank(message = "Email must not be blank")
        String email,

        @NotBlank(message = "Password must not be blank")
        String password,

        @NotNull(message = "Role must not be null")
        Role role
) {}
