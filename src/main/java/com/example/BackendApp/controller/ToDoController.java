package com.example.BackendApp.controller;

import com.example.BackendApp.entity.ToDoEntity;
import com.example.BackendApp.service.ToDoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class ToDoController {

    @Autowired
    private ToDoServiceImpl toDoService;

    @PostMapping
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity createToDo(@RequestBody ToDoEntity toDoEntity,
                                     @RequestParam Long userId) {
        try {
            return ResponseEntity.ok(toDoService.createToDo(toDoEntity, userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @PutMapping
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity updateToDo(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(toDoService.updateToDo(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}
