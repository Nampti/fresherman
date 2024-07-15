package com.fresher.fresherserivce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fresher.fresherserivce.mapper.UserMapper;
import com.fresher.fresherserivce.model.User;
import com.fresher.fresherserivce.repository.table.UserRepository;
import com.fresher.fresherserivce.vo.enums.CoreErrorApp;
import com.fresher.fresherserivce.vo.enums.Role;
import com.fresher.fresherserivce.vo.request.RegisterRequest;
import com.fresher.fresherserivce.vo.response.AuthResponse;
import com.fresher.fresherserivce.vo.response.UserResponse;

import static com.fresher.fresherserivce.vo.enums.CoreErrorApp.ERR_EXIST_ACCOUNT;
import static com.fresher.fresherserivce.vo.response.ResponseEntity.responseToClient;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final UserMapper userMapper;

    public AuthResponse login(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.map(user -> AuthResponse.builder()
                .user(UserResponse.toUserResponse(user))
                .token(jwtService.generateToken(user))
                .build()).orElse(null);
    }

    public Object saveUser(RegisterRequest request) {
        User userDB = userRepository.findByUsername(request.getUser().getUsername()).orElse(null);
        if (userDB != null) {
            return responseToClient(ERR_EXIST_ACCOUNT);
        }


        User user = request.getUser().convertToUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(user.getRole());
        User savedUser = userRepository.save(user);

        return UserResponse.toUserResponse(savedUser);
    }


}
