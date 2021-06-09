package com.example.user.service;

import com.example.user.request.UpdateUserRequest;
import com.example.user.response.PersistResponse;
import com.example.user.request.UserRequest;
import com.example.user.response.UserResponse;


public interface UserService {
    PersistResponse createUser(UserRequest userRequest);

    UserResponse getUser(String id);

    PersistResponse updateUser(UpdateUserRequest userRequest, String id);
}
