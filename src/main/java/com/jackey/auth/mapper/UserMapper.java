package com.jackey.auth.mapper;

import com.jackey.auth.dto.LoginResponse;
import com.jackey.auth.dto.SignupResponse;
import com.jackey.auth.entity.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    LoginResponse toLoginResponse(User user);

    SignupResponse toSignupResponse(User user);
}
