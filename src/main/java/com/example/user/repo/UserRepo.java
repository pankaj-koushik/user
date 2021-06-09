package com.example.user.repo;

import com.example.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author 000FIY744
 */
public interface UserRepo  extends MongoRepository<User,String> {

    User findByName(String name);
}
