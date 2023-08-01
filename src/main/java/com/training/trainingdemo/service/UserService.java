package com.training.trainingdemo.service;

import com.training.trainingdemo.model.User;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    User updateUser(User user);
    String deleteUserById(Long id);
}
