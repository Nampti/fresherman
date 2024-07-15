package com.fresher.fresherserivce.vo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fresher.fresherserivce.model.User;
import com.fresher.fresherserivce.vo.enums.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    private Long id;

    private String username;

    private String password;

    private List<Role> role;

    private String name;

    private String email;

    private String phoneNumber;

    public User convertToUser() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        String roles = "";
        for (Role rolex : role) {
            roles += rolex.name() + ";";
        }
        user.setRole(roles);
        return user;
    }
}
