package com.devjobs.adapters.in.rest.controller;

import com.devjobs.application.usecase.AuthenticationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthenticationController.class)
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authenticationService;

    @Test
    @DisplayName("Should return 200 when login is successful")
    void login_Success() throws Exception {
        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "email": "zuma@email.com",
                              "password": "123Senha"
                            }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Login successful"));

        verify(authenticationService).authenticate("zuma@email.com", "123Senha");
    }

    @Test
    @DisplayName("Should return 404 when user not found")
    void login_UserNotFound() throws Exception {
        doThrow(new RuntimeException("User not found"))
                .when(authenticationService).authenticate(anyString(), anyString());

        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "email": "notfound@email.com",
                              "password": "senha"
                            }
                        """))
                .andExpect(status().isInternalServerError());
    }
}