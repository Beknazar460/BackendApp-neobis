package com.example.BackendApp.controller;

import com.example.BackendApp.entity.UserEntity;
import com.example.BackendApp.exceptions.UserAlreadyExistException;
import com.example.BackendApp.exceptions.UserNotFoundException;
import com.example.BackendApp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/get-user-id")
        public ResponseEntity getUserId(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(userServiceImpl.getUserId(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @PostMapping("/create-user")
    public ResponseEntity createUser(@RequestBody UserEntity userEntity) {
        try {
            userServiceImpl.createUser(userEntity);
            return ResponseEntity.ok().body("Пользователь создан: " + userEntity.getUserName());
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity deleteUser(@RequestParam Long id) {
        try {
            return ResponseEntity.ok("Пользователь удален под номером: " + userServiceImpl.deleteUser(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}
