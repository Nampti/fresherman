package com.fresher.fresherserivce.vo.request;

import com.fresher.fresherserivce.model.User;
import com.fresher.fresherserivce.vo.dto.UserRequestDTO;

import lombok.Data;

@Data
public class RegisterRequest {

    private UserRequestDTO user;

}
