package com.jackey.auth.mapper;

import com.jackey.auth.dto.LoginResponse;
import com.jackey.auth.dto.SignupResponse;
import com.jackey.auth.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "id", target = "userId")
    LoginResponse toLoginResponse(User user);

    @Mapping(source = "id", target = "userId")
    SignupResponse toSignupResponse(User user);
}
