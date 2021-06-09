package com.example.user.service;

import com.example.user.model.User;
import com.example.user.repo.UserRepo;
import com.example.user.request.UpdateUserRequest;
import com.example.user.response.PersistResponse;
import com.example.user.request.UserRequest;
import com.example.user.response.UserDTO;
import com.example.user.response.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
public class UserServiceImpl implements UserService {


    final
    UserRepo userRepo;

    final
    ObjectMapper objectMapper;

    final MongoTemplate mongoTemplate;

    public UserServiceImpl(UserRepo userRepo, ObjectMapper objectMapper, MongoTemplate mongoTemplate) {
        this.userRepo = userRepo;
        this.objectMapper = objectMapper;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public PersistResponse createUser(UserRequest userRequest) {
        log.info("Creating user with name {}", userRequest.getName());
        PersistResponse persistResponse = new PersistResponse();
        User userObj = userRepo.findByName(userRequest.getName());
        mongoTemplate.getCollection("users");
        if (!ObjectUtils.isEmpty(userObj)) {
            persistResponse.setMessage("User found with same name");
            return persistResponse;
        }
        User user = objectMapper.convertValue(userRequest, User.class);
        user.setId("EMP-" + UUID.randomUUID().toString());
        user.setPersistedDate(LocalDateTime.now());
        user.setModifiedDate(LocalDateTime.now());

        persistResponse.setId(user.getId());
        persistResponse.setMessage("Saved Successfully");
        userRepo.save(user);
        log.info("Saved successfully into db with id {}", user.getId());

        return persistResponse;
    }

    @Override
    public UserResponse getUser(String id) {

        UserResponse userResponse = new UserResponse();
        log.info("Finding user with id {}", id);
        Optional<User> userOp = userRepo.findById(id);
        if (!userOp.isPresent()) {
            userResponse.setMessage("No Records Found");
            return userResponse;
        }
        User userObj = userOp.get();
        UserDTO userDTO = objectMapper.convertValue(userObj, UserDTO.class);

        userResponse.setUser(userDTO);
        return userResponse;
    }

    @Override
    public PersistResponse updateUser(UpdateUserRequest userRequest, String id) {
        log.info("updating user with id {}", id);
        PersistResponse persistResponse = new PersistResponse();
        Optional<User> userOp = userRepo.findById(id);
        if (!userOp.isPresent()) {
            persistResponse.setMessage("No Records Found");
            return persistResponse;
        }
        User userObj = userOp.get();
        userObj.setModifiedDate(LocalDateTime.now());
        userObj.setAddress(userRequest.getAddress());
        userRepo.save(userObj);
        log.info("User Updated successfully  with id {}",id);
        persistResponse.setId(userObj.getId());
        return persistResponse;
    }
}
