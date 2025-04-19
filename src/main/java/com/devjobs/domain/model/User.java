package com.devjobs.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="user")
@Table(name="tb_user")
public class User {

    @Id
    private UUID id;
    private String name;
    private String email;
    private String password;
    private Role role;

    public User(String name, String email, String password, Role role) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }


}