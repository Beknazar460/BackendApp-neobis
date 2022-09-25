package com.example.BackendApp.controller;

import com.example.BackendApp.entity.UserEntity;
import com.example.BackendApp.exceptions.UserAlreadyExistException;
import com.example.BackendApp.exceptions.UserNotFoundException;
import com.example.BackendApp.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Пользовательский контроллер", description = "контроллер для работы с данными пользователя")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/{id}")
    @Operation(summary = "Получение пользователя")
        public ResponseEntity getUserId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userServiceImpl.getUserId(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @PostMapping("/create")
    @Operation(summary = "Создать пользователя")
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

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление пользователя")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok("Пользователь удален под номером: " + userServiceImpl.deleteUser(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}
