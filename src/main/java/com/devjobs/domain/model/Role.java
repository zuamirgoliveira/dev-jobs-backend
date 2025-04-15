package com.devjobs.domain.model;

import lombok.Getter;

@Getter
public enum Role {

    CANDIDATE("Candidate"),
    RECRUITER("Recruiter");

    private final String description;

    Role(String description) {
        this.description = description;
    }

}
