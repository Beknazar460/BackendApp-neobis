package com.example.BackendApp.service.impl;

import com.example.BackendApp.entity.OrderEntity;
import com.example.BackendApp.model.OrderModel;
import org.springframework.http.ResponseEntity;

public interface OrderService {

    ResponseEntity<?> createOrder(OrderModel orderModel);
    ResponseEntity<String> deleteOrder(Long id);

}
