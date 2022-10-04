package com.example.BackendApp.controller;

import com.example.BackendApp.entity.LapTopEntity;
import com.example.BackendApp.model.LapTopModel;
import com.example.BackendApp.service.LapTopServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/laptops")
@Tag(
        name = "Контроллер для управления записей товаров",
        description = "В этом контроллере вы сможете добавлять, удалять, получать, а также обновлять данные товара"
)
public class LapTopController {

    private final LapTopServiceImpl lapTopService;

    @Autowired
    public LapTopController(LapTopServiceImpl lapTopService) {
        this.lapTopService = lapTopService;
    }

    @GetMapping
    @Operation(
            summary = "Получение всех товаров",
            description = "Позволяет получить всех товаров"
    )
    private ResponseEntity getAllLapTops() {
        return ResponseEntity.ok(lapTopService.getALlLapTops());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение товара",
            description = "Позволяет получить товар по идентификатору"
    )
    private ResponseEntity getLaptopById(@PathVariable
                                             @Parameter(description = "Идентификатор товара")
                                                Long id) {
       return lapTopService.getLapTopId(id);
    }

    @PostMapping
    @Operation(
            summary = "Создание товара",
            description = "Позволяет создать товар введя его данные"
    )
    private ResponseEntity createLapTop(@RequestBody LapTopModel lapTopModel) {
        return lapTopService.createLapTop(lapTopModel);
    }

    @PutMapping("/{id}")
    @Operation (
            summary = "Обновление товара" ,
            description = "Позволяет изменить данные товара по идентификатору"
    )
    private ResponseEntity updateLapTop(@PathVariable
                                            @Parameter(description = "Идентификатор товара")
                                                Long id,
                                        @RequestBody LapTopModel lapTopModel) {
        return lapTopService.updateLapTop(id, lapTopModel);
    }

    @DeleteMapping("/{id}")
    @Operation (
            summary = "Удаление товара",
            description = "Позволяет удалить товар по идентификатору"
    )
    private ResponseEntity deleteLapTop(@PathVariable
                                            @Parameter(description = "Идентификатор товара")
                                                Long id) {
        return lapTopService.deleteLapTop(id);
    }
}
