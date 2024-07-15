package com.fresher.fresherserivce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.fresher.fresherserivce.mapper.UserMapper;
import com.fresher.fresherserivce.repository.table.UserRepository;
import com.fresher.fresherserivce.vo.response.UserResponse;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse getUserDetail(Long id) {
        return UserResponse.toUserResponse(userRepository.findById(id).orElseThrow());
    }

}
