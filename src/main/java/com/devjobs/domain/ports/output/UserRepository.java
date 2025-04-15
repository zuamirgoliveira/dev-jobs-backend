package com.devjobs.domain.ports.output;

import com.devjobs.domain.model.User;

public interface UserRepository {
    User save(User user);
}
