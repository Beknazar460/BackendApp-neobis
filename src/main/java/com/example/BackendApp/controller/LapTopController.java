package com.example.BackendApp.controller;

import com.example.BackendApp.entity.LapTopEntity;
import com.example.BackendApp.exceptions.LapTopNotFoundException;
import com.example.BackendApp.service.LapTopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/laptops")
public class LapTopController {

    @Autowired
    LapTopServiceImpl lapTopService;

    @GetMapping("/{id}")
    private ResponseEntity getLaptopById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(lapTopService.getLapTopId(id));
        } catch (LapTopNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @GetMapping
    private ResponseEntity getLapTopByTitle(@RequestParam String title) {
        try {
            return ResponseEntity.ok(lapTopService.getLapTopTitle(title));
        } catch (LapTopNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @PostMapping
    private ResponseEntity createLapTop(@RequestBody LapTopEntity lapTopEntity) {
        try {
            lapTopService.createLapTop(lapTopEntity);
            return ResponseEntity.ok("Товар успешно внесён: " + lapTopEntity.getTitle());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка при внесении товара");
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deleteLapTop(@PathVariable Long id) {
        try {
            return ResponseEntity.ok("Товар успешно удалён под номером: " + lapTopService.deleteLapTop(id));
        } catch (LapTopNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
