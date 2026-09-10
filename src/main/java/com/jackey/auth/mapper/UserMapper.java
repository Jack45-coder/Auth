package com.jackey.auth.mapper;

import com.jackey.auth.dto.AuthResponse;
import com.jackey.auth.entity.User;

public class UserMapper {

    public static AuthResponse toAuthResponse(User user){
        return new AuthResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
