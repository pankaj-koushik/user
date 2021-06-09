package com.example.user.request;


import com.example.user.model.Address;
import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private Address address;
}
