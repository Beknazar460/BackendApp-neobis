package com.example.BackendApp.controller;

import com.example.BackendApp.entity.OrderEntity;
import com.example.BackendApp.model.OrderModel;
import com.example.BackendApp.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/orders")
public class OrderController {

    private final OrderServiceImpl orderService;

    @Autowired
    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }


    @PostMapping
    private ResponseEntity createOrder(@RequestBody OrderModel order) {
        return orderService.createOrder(order);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deleteOrder(@PathVariable Long id) {
        return orderService.deleteOrder(id);
    }

}
