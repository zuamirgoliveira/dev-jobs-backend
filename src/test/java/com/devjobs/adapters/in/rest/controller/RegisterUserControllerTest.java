package com.devjobs.adapters.in.rest.controller;

import com.devjobs.application.usecase.RegisterUserService;
import com.devjobs.domain.exception.UserNotFoundException;
import com.devjobs.domain.model.Role;
import com.devjobs.domain.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegisterUserController.class)
class RegisterUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegisterUserService registerUserService;

    @Test
    @DisplayName("Should register user successfully and return 201")
    void registerUser_Success() throws Exception {
        User user = new User("Zuma", "zuma@gmail.com", "123Senha", Role.CANDIDATE);

        when(registerUserService.register(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name": "Zuma",
                                "email": "zuma@gmail.com",
                                "password": "123Senha",
                                "role": "CANDIDATE"
                            }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Zuma"))
                .andExpect(jsonPath("$.email").value("zuma@gmail.com"))
                .andExpect(jsonPath("$.role").value("CANDIDATE"));
    }

    @Test
    @DisplayName("Should return 400 Bad Request for invalid input")
    void registerUser_InvalidInput() throws Exception {
        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name": "",
                                "email": "invalid",
                                "password": "",
                                "role": null
                            }
                        """))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should return user data when user exists")
    void getUserById_Success() throws Exception {
        User user = new User("Zuma", "zuma@gmail.com", "123Senha", Role.CANDIDATE);
        String userId = user.getId().toString();

        when(registerUserService.findUserById(any(UUID.class))).thenReturn(user);

        mockMvc.perform(get("/api/v1/users/" + userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.name").value("Zuma"))
                .andExpect(jsonPath("$.email").value("zuma@gmail.com"))
                .andExpect(jsonPath("$.role").value("CANDIDATE"));
    }

    @Test
    @DisplayName("Should return 404 when user does not exist")
    void getUserById_NotFound() throws Exception {
        UUID userId = UUID.randomUUID();

        when(registerUserService.findUserById(userId))
                .thenThrow(new UserNotFoundException("User with ID " + userId + " not found"));


        mockMvc.perform(get("/api/v1/users/" + userId))
                .andExpect(status().isNotFound());
    }

}
