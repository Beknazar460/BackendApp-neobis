package com.example.BackendApp.service.impl;

import com.example.BackendApp.entity.UserEntity;
import com.example.BackendApp.exceptions.UserAlreadyExistException;
import com.example.BackendApp.exceptions.UserNotFoundException;
import com.example.BackendApp.model.UserModel;

import java.util.HashSet;

public interface UserService {

    void createUser(UserEntity user) throws UserAlreadyExistException;
    UserModel getUserId(Long id) throws UserNotFoundException;
    UserModel getUserName(String name) throws UserNotFoundException;
    Long deleteUser(Long id);

}
