package com.jackey.auth.controller;

import com.jackey.auth.dto.LoginRequest;
import com.jackey.auth.dto.LoginResponse;
import com.jackey.auth.dto.SignupRequest;
import com.jackey.auth.dto.SignupResponse;
import com.jackey.auth.response.ApiResponse;
import com.jackey.auth.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ApiResponse<SignupRequest> signup(@Valid @RequestBody SignupRequest request){
        SignupResponse response = userService.register(request);
        return new ApiResponse<>(true, "Registration Successfully", response);
    }

    @PostMapping("/login")
    public ApiResponse<LoginRequest> login(@Valid @RequestBody LoginRequest request){
        LoginResponse response = userService.login(request);
        return ApiResponse.success("Login Successfully", response);
    }
}
