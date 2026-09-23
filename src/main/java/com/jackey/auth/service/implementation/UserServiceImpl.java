package com.jackey.auth.service.implementation;
import com.jackey.auth.dto.LoginRequest;
import com.jackey.auth.dto.LoginResponse;
import com.jackey.auth.dto.SignupRequest;
import com.jackey.auth.dto.SignupResponse;
import com.jackey.auth.entity.User;
import com.jackey.auth.exception.AuthException;
import com.jackey.auth.mapper.UserMapper;
import com.jackey.auth.repository.UserRepository;
import com.jackey.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    public SignupResponse register(SignupRequest request){

        // 1. Request validation
        if (request == null){
            throw new AuthException("Request Cannot be Null");
        }

        // 2. Email Validation
        userRepository.findByUsername(request.getUsername()).ifPresent(it -> {
            throw new AuthException("username Already Exists!");
        });

        // Create new user (Entity)
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());

        // encoded password save in db
        String encodedPass = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPass);
        user.setActive(true);

        User savedUser;

        try {
            savedUser = userRepository.save(user);
        }catch (Exception e){
            throw new AuthException("Error While Save User...");
        }

        return userMapper.toSignupResponse(savedUser);
    }

    public LoginResponse login(LoginRequest request){

        // 1. Request validation
        if (request == null){
            throw new AuthException("Request Cannot be Null");
        }

        // 3. find user by email
        User validUser = userRepository.findByUsername(request.getUsername()).orElseThrow(
                () -> new AuthException("Invalid user or username mismatch...")
        );

        // 4. Check password
        boolean passwordMatch = passwordEncoder.matches(
                request.getPassword(),
                validUser.getPassword()
        );
        if(!passwordMatch){
            throw new AuthException("Invalid username or password");
        }

        // 5. Check user active or not
        if(!validUser.isActive()){
            throw new AuthException("User inactive...");
        }

        return userMapper.toLoginResponse(validUser);
    }
}
