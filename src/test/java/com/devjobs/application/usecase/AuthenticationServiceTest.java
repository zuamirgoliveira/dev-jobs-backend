package com.devjobs.application.usecase;

import com.devjobs.domain.model.Role;
import com.devjobs.domain.model.User;
import com.devjobs.domain.ports.output.UserRepository;
import com.devjobs.domain.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthenticationServiceTest {

    private AuthenticationService authenticationService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        authenticationService = new AuthenticationService(userRepository);
    }

    @Test
    void authenticate_ShouldPass_WhenCredentialsAreValid() {
        User user = new User("Zuma", "zuma@email.com", "123Senha", Role.CANDIDATE);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        assertDoesNotThrow(() -> authenticationService.authenticate(user.getEmail(), user.getPassword()));
    }

    @Test
    void authenticate_ShouldThrow_WhenUserNotFound() {
        when(userRepository.findByEmail("invalid@email.com")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () ->
                authenticationService.authenticate("invalid@email.com", "anyPassword"));
    }

    @Test
    void authenticate_ShouldThrow_WhenPasswordDoesNotMatch() {
        User user = new User("Zuma", "zuma@email.com", "123Senha", Role.CANDIDATE);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        String email = user.getEmail();

        assertThrows(IllegalArgumentException.class, () ->
                authenticationService.authenticate(email, "wrongPassword")
        );
    }

}