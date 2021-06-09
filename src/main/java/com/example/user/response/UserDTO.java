package com.example.user.response;

import com.example.user.model.Address;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {

    private String userId;
    private String name;
    private Address address;
    private LocalDateTime persistedDate;
    private LocalDateTime modifiedDate;

    public void setId(String id){
        this.userId = id;
    }
}
