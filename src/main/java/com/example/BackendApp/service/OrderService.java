package com.example.BackendApp.service;

import com.example.BackendApp.entity.OrderEntity;
import com.example.BackendApp.model.OrderRequest;
import com.example.BackendApp.repository.LaptopRepo;
import com.example.BackendApp.repository.OrderRepo;
import com.example.BackendApp.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepo orderRepo;
    private final LaptopRepo lapTopRepo;
    private final UserRepo userRepo;

    public ResponseEntity<?> createOrder(OrderRequest orderRequest) {
        if (userRepo.findById(orderRequest.getUsersId()).isPresent() && lapTopRepo.findByTitle(orderRequest.getTitleOfProduct()).isPresent()) {
            OrderEntity orderEntity = new OrderEntity(orderRequest.getTitleOfProduct(), lapTopRepo.findByTitle(orderRequest.getTitleOfProduct()).get().getPrice(),
                    userRepo.findById(orderRequest.getUsersId()).get());
            orderRepo.save(orderEntity);
            return ResponseEntity.ok("Order is created");
        }
        return new ResponseEntity<String>("Such a user or product does not exist", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteOrder(Long id) {
        if (orderRepo.existsById(id)) {
            orderRepo.deleteById(id);
            return ResponseEntity.ok("Order is deleted");
        }
        else return new ResponseEntity<String>("There is no such order", HttpStatus.NOT_FOUND);
    }
}
