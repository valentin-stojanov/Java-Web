package com.example.pathfinder.service;

import com.example.pathfinder.model.service.UserServiceModel;


public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByUserNameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    void logout();

    UserServiceModel findById(Long id);
}
