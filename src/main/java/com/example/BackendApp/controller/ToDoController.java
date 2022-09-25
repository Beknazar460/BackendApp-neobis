package com.example.BackendApp.controller;

import com.example.BackendApp.entity.ToDoEntity;
import com.example.BackendApp.service.ToDoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
@Api(description = "Контроллер для управления данных с задачками пользователя")
public class ToDoController {

    @Autowired
    private ToDoServiceImpl toDoService;

    @PostMapping
    @ApiOperation("Создание задачи")
    public ResponseEntity createToDo(@RequestBody ToDoEntity toDoEntity,
                                     @RequestParam Long userId) {
        try {
            return ResponseEntity.ok(toDoService.createToDo(toDoEntity, userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @PutMapping
    @ApiOperation("обновление задачи")
    public ResponseEntity updateToDo(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(toDoService.updateToDo(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}
