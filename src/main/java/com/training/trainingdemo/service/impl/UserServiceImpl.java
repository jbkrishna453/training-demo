package com.training.trainingdemo.service.impl;

import com.training.trainingdemo.model.User;
import com.training.trainingdemo.service.UserService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private static List<User> userList= new ArrayList<>();
    @Override
    public User createUser(User user) {
        userList.add(user);
        return user;
    }

    @Override
    public User getUserById(Long id) {
      return  userList.stream()
              .filter(user->user.getId().equals(id))
              .findFirst().orElseThrow(()->new RuntimeException("message"));
    }

    @Override
    public User  updateUser(User updatedUser) {
         userList.forEach(user->
        {
            if(user.getId().equals(updatedUser.getId())){
                user.setEmailId(updatedUser.getEmailId());
                user.setFirstName(updatedUser.getFirstName());
                user.setPhoneNo(updatedUser.getPhoneNo());
                user.setLastName(updatedUser.getLastName());
            }
        });
         return updatedUser;
    }

    @Override
    public String deleteUserById(Long id) {
        userList= userList.stream().filter(user -> !user.getId().equals(id)).collect(Collectors.toList());
        return "SUCCESS";
    }
}
