package com.jackey.auth.service;

import com.jackey.auth.dto.LoginRequest;
import com.jackey.auth.dto.LoginResponse;
import com.jackey.auth.dto.SignupRequest;
import com.jackey.auth.dto.SignupResponse;
import org.springframework.stereotype.Service;


@Service
public interface UserService {
    SignupResponse register(SignupRequest request);

    LoginResponse login(LoginRequest request);
}
