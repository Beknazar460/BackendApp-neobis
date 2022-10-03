package com.example.BackendApp.controller;

import com.example.BackendApp.entity.UserEntity;
import com.example.BackendApp.model.UserModel;
import com.example.BackendApp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserId(@PathVariable Long id) {
        return userServiceImpl.getUserId(id);
    }

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userServiceImpl.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserEntity userEntity) {
        return userServiceImpl.createUser(userEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,
                                         @RequestBody UserEntity user) {
        return userServiceImpl.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        return userServiceImpl.deleteUser(id);
    }
}
