package com.example.BackendApp.service.impl;

import com.example.BackendApp.entity.UserEntity;
import com.example.BackendApp.model.UserModel;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface UserService {
    List<UserEntity> getAllUsers();
    ResponseEntity<?> createUser(UserEntity userEntity);
    ResponseEntity<?> getUserId(Long id);
    ResponseEntity<String> deleteUser(Long id);
    ResponseEntity<?> updateUser(Long id, UserEntity userEntity);

}
