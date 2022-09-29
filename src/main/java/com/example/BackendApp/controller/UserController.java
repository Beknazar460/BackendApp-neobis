package com.example.BackendApp.controller;

import com.example.BackendApp.entity.UserEntity;
import com.example.BackendApp.exceptions.UserAlreadyExistException;
import com.example.BackendApp.exceptions.UserNotFoundException;
import com.example.BackendApp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('users:read')")
        private ResponseEntity getUserId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userServiceImpl.getUserId(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('users:read')")
    private ResponseEntity getUserName(@RequestParam String userName) {
        try {
            return ResponseEntity.ok(userServiceImpl.getUserName(userName));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('users:write')")
    private ResponseEntity createUser(@RequestBody UserEntity userEntity) {
        try {
            userServiceImpl.createUser(userEntity);
            return ResponseEntity.ok().body("Пользователь создан: " + userEntity.getUserName());
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    private ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok("Пользователь удален под номером: " + userServiceImpl.deleteUser(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}
