package com.example.user.api;


import com.example.user.request.UpdateUserRequest;
import com.example.user.response.PersistResponse;
import com.example.user.request.UserRequest;
import com.example.user.response.UserResponse;
import com.example.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserRestService {

    final
    UserService userService;

    public UserRestService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PersistResponse> createUser(@RequestBody UserRequest userRequest
    ) {

        log.debug("Create user  createUser received. {}", userRequest);


        PersistResponse response = userService.createUser(userRequest);


        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @RequestMapping( value = "/{id}",method = RequestMethod.PATCH)
    public ResponseEntity<PersistResponse> updateUser(@RequestBody UpdateUserRequest userRequest, @PathVariable String id
    ) {

        log.debug("Create user  createUser received. {}", userRequest);


        PersistResponse response = userService.updateUser(userRequest,id);


        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @RequestMapping( value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<UserResponse> getUser(@PathVariable String id
    )  {

        log.debug("get user  request received. {}", id);


        UserResponse response = userService.getUser(id);


        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
