package com.devjobs.application.usecase;

import com.devjobs.domain.model.Role;
import com.devjobs.domain.model.User;
import com.devjobs.domain.ports.output.UserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegisterUserServiceTest {

    @Test
    void shouldRegisterUserSuccessfully() {
        // Arrange
        UserRepository mockRepo = mock(UserRepository.class);
        RegisterUserService service = new RegisterUserService(mockRepo);

        User inputUser = new User("Zuma", "zuma@email.com", "password123", Role.CANDIDATE);
        User savedUser = new User(inputUser.getId(), "Zuma", "zuma@email.com", "password123", Role.CANDIDATE);

        when(mockRepo.save(inputUser)).thenReturn(savedUser);

        // Act
        User result = service.register(inputUser);

        // Assert
        assertNotNull(result.getId());
        assertEquals("Zuma", result.getName());
    }
}
