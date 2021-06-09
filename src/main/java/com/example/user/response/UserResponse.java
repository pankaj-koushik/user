package com.example.user.response;

import lombok.Data;

@Data
public class UserResponse {
    private String message;
    private UserDTO user;
}
