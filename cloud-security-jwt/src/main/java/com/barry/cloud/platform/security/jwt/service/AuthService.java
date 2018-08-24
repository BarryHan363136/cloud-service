package com.barry.cloud.platform.security.jwt.service;

import dev.local.user.User;

public interface AuthService {
    User register(User userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}
