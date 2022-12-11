package com.example.BackendApp.controllers.rest_controllers;

import com.example.BackendApp.entity.LaptopEntity;
import com.example.BackendApp.model.LaptopModel;
import com.example.BackendApp.service.LaptopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/laptops")
@Tag(
        name = "Контроллер для управления записей товаров",
        description = "В этом контроллере вы сможете добавлять, удалять, получать, а также обновлять данные товара"
)
public class RestLapTopController {

    private final LaptopService lapTopService;

    @GetMapping
    @Operation(
            summary = "Получение всех товаров",
            description = "Позволяет получить всех товаров"
    )
    @PreAuthorize("hasAuthority('users:read')")
    private List<LaptopEntity> getAllLapTops() {
        return lapTopService.getALlLapTops();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение товара",
            description = "Позволяет получить товар по идентификатору"
    )
    @PreAuthorize("hasAuthority('users:read')")
    private ResponseEntity<?> getLaptopById(@PathVariable
                                            @Parameter(description = "Идентификатор товара")
                                            Long id) {
        return lapTopService.getLapTopId(id);
    }

    @PostMapping
    @Operation(
            summary = "Создание товара",
            description = "Позволяет создать товар введя его данные"
    )
    @PreAuthorize("hasAuthority('users:write')")
    private ResponseEntity<?> createLapTop(@RequestBody LaptopModel lapTopModel) {
        return lapTopService.createLapTop(lapTopModel);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Обновление товара",
            description = "Позволяет изменить данные товара по идентификатору"
    )
    @PreAuthorize("hasAuthority('users:write')")
    private ResponseEntity<?> updateLapTop(@PathVariable
                                           @Parameter(description = "Идентификатор товара")
                                           Long id,
                                           @RequestBody LaptopModel lapTopModel) {
        return lapTopService.updateLapTop(id, lapTopModel);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление товара",
            description = "Позволяет удалить товар по идентификатору"
    )
    @PreAuthorize("hasAuthority('users:write')")
    private ResponseEntity<String> deleteLapTop(@PathVariable
                                                @Parameter(description = "Идентификатор товара")
                                                Long id) {
        return lapTopService.deleteLapTop(id);
    }
}
