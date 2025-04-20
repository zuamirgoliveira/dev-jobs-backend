package com.devjobs.application.usecase;

import com.devjobs.domain.exception.UserNotFoundException;
import com.devjobs.domain.model.Role;
import com.devjobs.domain.model.User;
import com.devjobs.domain.ports.output.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

class RegisterUserServiceTest {

    private RegisterUserService registerUserService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        registerUserService = new RegisterUserService(userRepository);
    }

    @Test
    @DisplayName("Should save user successfully")
    void register_ShouldSaveUser() {
        User user = new User("Zuma", "zuma@gmail.com", "123Senha", Role.CANDIDATE);

        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = registerUserService.register(user);

        assertThat(result).isEqualTo(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    @DisplayName("Should return user when found by ID")
    void findUserById_ShouldReturnUser_WhenExists() {
        User user = new User("Zuma", "zuma@gmail.com", "123Senha", Role.CANDIDATE);
        UUID userId = user.getId();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User result = registerUserService.findUserById(userId);

        assertThat(result).isEqualTo(user);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    @DisplayName("Should throw UserNotFoundException when user not found")
    void findUserById_ShouldThrowException_WhenNotFound() {
        UUID userId = UUID.randomUUID();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> registerUserService.findUserById(userId))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining("User with ID " + userId + " not found");

        verify(userRepository, times(1)).findById(userId);
    }
}
