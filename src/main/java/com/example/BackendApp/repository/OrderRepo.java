package com.example.BackendApp.repository;

import com.example.BackendApp.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderEntity, Long> {
}
