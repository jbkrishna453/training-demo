package com.training.trainingdemo.controller;

import com.training.trainingdemo.model.User;
import com.training.trainingdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
   private final UserService userService;

    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }
    @PutMapping("/users")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }



    @DeleteMapping("/users/{id}")
    public String  deleteByUserId(@PathVariable Long id){
        return userService.deleteUserById(id);
    }

}
