package com.jackey.auth.service;

import com.jackey.auth.dto.AuthResponse;
import com.jackey.auth.dto.LoginRequest;
import com.jackey.auth.dto.SignupRequest;
import org.springframework.stereotype.Service;


@Service
public interface UserService {
    AuthResponse register(SignupRequest request);

    AuthResponse login(LoginRequest request);
}
