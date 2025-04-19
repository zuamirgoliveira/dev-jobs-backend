package com.devjobs.adapters.in.rest.dto;

public record RegisterUserResponse(
        String id,
        String name,
        String email,
        String role
) {}
