package com.jackey.auth.controller;

import com.jackey.auth.dto.AuthResponse;
import com.jackey.auth.dto.LoginRequest;
import com.jackey.auth.dto.SignupRequest;
import com.jackey.auth.response.ApiResponse;
import com.jackey.auth.service.UserService;
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
    public ApiResponse<AuthResponse> signup(@RequestBody SignupRequest request){
        AuthResponse response = userService.register(request);
        return new ApiResponse<>(true, "Registration Successfully", response);
    }

    @PostMapping("/signin")
    public ApiResponse<AuthResponse> signin(@RequestBody LoginRequest request){
        AuthResponse response = userService.signin(request);
        return ApiResponse.success("Login Successfully", response);
    }
}
