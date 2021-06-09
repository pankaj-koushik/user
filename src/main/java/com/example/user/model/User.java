package com.example.user.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author 000FIY744
 */
@Data
@Document(collection ="users")
public class User {

    @Id
    private String id;
    private String name;
    private Address address;
    private LocalDateTime persistedDate;
    private LocalDateTime modifiedDate;

}
