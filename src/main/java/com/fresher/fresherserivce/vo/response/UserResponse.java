package com.fresher.fresherserivce.vo.response;

import lombok.Data;

import java.util.Collection;
import java.util.List;

import com.fresher.fresherserivce.model.User;
import com.fresher.fresherserivce.vo.enums.Role;
import com.fresher.fresherserivce.vo.until.Common;

@Data
public class UserResponse {

    private Long id;

    private String username;

    private List<Role> roles;

    private String name;

    private String email;

    private String phoneNumber;

    public static UserResponse toUserResponse(User user) {
        if (user == null) {
            return null;
        }

        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setRoles(Common.convertStringToRoles(user.getRole()));
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhoneNumber(user.getPhoneNumber());

        return userResponse;
    }

}
