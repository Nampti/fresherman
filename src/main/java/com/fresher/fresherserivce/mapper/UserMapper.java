package com.fresher.fresherserivce.mapper;

import org.mapstruct.Mapper;

import com.fresher.fresherserivce.model.User;
import com.fresher.fresherserivce.vo.response.UserResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(User user);
}
