package com.example.BackendApp.controller;

import com.example.BackendApp.entity.LapTopEntity;
import com.example.BackendApp.model.LapTopModel;
import com.example.BackendApp.service.LapTopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/laptops")
public class LapTopController {

    private final LapTopServiceImpl lapTopService;

    @Autowired
    public LapTopController(LapTopServiceImpl lapTopService) {
        this.lapTopService = lapTopService;
    }

    @GetMapping
    private ResponseEntity getAllLapTops() {
        return ResponseEntity.ok(lapTopService.getALlLapTops());
    }

    @GetMapping("/{id}")
    private ResponseEntity getLaptopById(@PathVariable Long id) {
       return lapTopService.getLapTopId(id);
    }

    @PostMapping
    private ResponseEntity createLapTop(@RequestBody LapTopModel lapTopModel) {
        return lapTopService.createLapTop(lapTopModel);
    }

    @PutMapping("/{id}")
    private ResponseEntity updateLapTop(@PathVariable Long id, LapTopModel lapTopModel) {
        return lapTopService.updateLapTop(id, lapTopModel);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deleteLapTop(@PathVariable Long id) {
        return lapTopService.deleteLapTop(id);
    }
}
