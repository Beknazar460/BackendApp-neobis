package com.example.BackendApp.controllers.rest_controllers;

import com.example.BackendApp.model.OrderModel;
import com.example.BackendApp.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/orders")
@Tag (
        name = "Контроллер для управления с заказами",
        description = "В этом контроллере вы сможете создавать и удалять заказы"
)
public class RestOrderController {

    private final OrderService orderService;

    @Autowired
    public RestOrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping
    @Operation(
            summary = "Создание заказа",
            description = "Позволяет создать заказ введя его данные"
    )
    @PreAuthorize("hasAuthority('users:write')")
    private ResponseEntity<?> createOrder(@RequestBody OrderModel order) {
        return orderService.createOrder(order);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление заказа",
            description = "Позволяет удалять заказы по идентификатору"
    )
    @PreAuthorize("hasAuthority('users:write')")
    private ResponseEntity<?> deleteOrder(@PathVariable
                                           @Parameter(description = "Идентификатор заказа")
                                                Long id) {
        return orderService.deleteOrder(id);
    }

}
