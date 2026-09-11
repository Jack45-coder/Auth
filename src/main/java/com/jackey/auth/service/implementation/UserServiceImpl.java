package com.jackey.auth.service.implementation;
import com.jackey.auth.dto.AuthResponse;
import com.jackey.auth.dto.LoginRequest;
import com.jackey.auth.dto.SignupRequest;
import com.jackey.auth.entity.User;
import com.jackey.auth.exception.AuthException;
import com.jackey.auth.mapper.UserMapper;
import com.jackey.auth.repository.UserRepository;
import com.jackey.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public AuthResponse register(SignupRequest request){

        // 1. Request validation
        if (request == null){
            throw new AuthException("Request Cannot be Null");
        }

        // 2. Field validation
        if(request.getName() == null ||
                request.getEmail() == null ||
                request.getPassword() == null){

            throw new AuthException(
                    "All fields (username, email, password) are required"
            );
        }

        // 3. Email Validation
        userRepository.findByEmail(request.getEmail()).ifPresent(it -> {
            throw new AuthException("Email Already Exists!");
        });

        // Create new user (Entity)
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setActive(true);

        User savedUser;

        try {
            savedUser = userRepository.save(user);
        }catch (Exception e){
            throw new AuthException("Error While Save User...");
        }

        return UserMapper.toAuthResponse(savedUser);
    }

    public AuthResponse signin(LoginRequest request){

        // 1. Request validation
        if (request == null){
            throw new AuthException("Request Cannot be Null");
        }

        // 2. Field validation
        if(request.getEmail() == null || request.getPassword() == null){
            throw new AuthException("Email and Password are required");
        }

        // 3. find user by email
        User validUser = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new AuthException("Invalid User or Email mismatch...")
        );

        // 4. Check password
        if(!validUser.getPassword().equals(request.getPassword())){
            throw new AuthException("Invalid Email or Password");
        }

        // 5. Check user active or not
        if(!validUser.isActive()){
            throw new AuthException("User inactive...");
        }

        return UserMapper.toAuthResponse(validUser);
    }
}
