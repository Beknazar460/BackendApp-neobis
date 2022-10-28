package com.example.BackendApp.service;

import com.example.BackendApp.entity.UserEntity;
import com.example.BackendApp.model.UserRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface UserService {
    List<UserEntity> getAllUsers();
    ResponseEntity<?> createUser(UserRequest userRequest);
    ResponseEntity<?> getUserId(Long id);
    ResponseEntity<String> deleteUser(Long id);
    ResponseEntity<?> updateUser(Long id, UserRequest userRequest);
}
