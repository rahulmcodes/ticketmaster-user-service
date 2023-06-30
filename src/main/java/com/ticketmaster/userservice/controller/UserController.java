package com.ticketmaster.userservice.controller;

import com.ticketmaster.userservice.Exceptions.UserAlreadyExistsException;
import com.ticketmaster.userservice.records.User;
import com.ticketmaster.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //should return user
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUser(@RequestParam("id") Long id) {

       try{
           User user = userService.getUser(id);
           return ResponseEntity.ok().body(user);

       } catch (EntityNotFoundException e) {
           return ResponseEntity.notFound().build();
       }

    }

    @PostMapping("/")
    public ResponseEntity<String> saveUser(@RequestBody @Validated User user) {
        //saves user
        String response = "";
        if(!Objects.isNull(user)) {
            com.ticketmaster.userservice.models.User user1 = new com.ticketmaster.userservice.models.User(user.userName(), user.password(), user.email(), user.firstName(), user.lastName());

            try {
                response = userService.saveUser(user1);
            } catch (UserAlreadyExistsException e){
                return ResponseEntity.badRequest().body(e.getMessage());
            }

        }
        //maybe return 201
        return ResponseEntity.ok(response);
    }

//    @PutMapping("/")
//    public void updateUser(@RequestBody User user,  ) {
//        //update user details
//        if(!Objects.isNull(user))
//            userService.updateUser(user);
//
//    }


}
