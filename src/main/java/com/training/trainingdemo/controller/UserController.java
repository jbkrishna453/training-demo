package com.training.trainingdemo.controller;

import com.training.trainingdemo.model.User;
import com.training.trainingdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
   private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
     return  new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return  new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }
    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        return  new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }
    @DeleteMapping("/users/{id}")
    public String  deleteByUserId(@PathVariable Long id){
        return userService.deleteUserById(id);
    }

}
