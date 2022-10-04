package com.example.BackendApp.service;

import com.example.BackendApp.entity.OrderEntity;
import com.example.BackendApp.model.OrderModel;
import com.example.BackendApp.repository.LapTopRepo;
import com.example.BackendApp.repository.OrderRepo;
import com.example.BackendApp.repository.UserRepo;
import com.example.BackendApp.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final LapTopRepo lapTopRepo;
    private final UserRepo userRepo;
    @Autowired
    public OrderServiceImpl(OrderRepo orderRepo, LapTopRepo lapTopRepo, UserRepo userRepo) {
        this.orderRepo = orderRepo;
        this.lapTopRepo = lapTopRepo;
        this.userRepo = userRepo;
    }


    @Override
    public ResponseEntity<?> createOrder(OrderModel orderModel) {
        if (userRepo.findById(orderModel.getUsersId()).isPresent() && lapTopRepo.findById(orderModel.getLaptopsId()).isPresent()) {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setTitleOfProduct(orderModel.getTitleOfProduct());
            orderEntity.setPriceOfProduct(orderModel.getPriceOfProduct());
            orderEntity.setUser(userRepo.findById(orderModel.getUsersId()).get());
            orderEntity.setLapTop(lapTopRepo.findById(orderModel.getLaptopsId()).get());
            orderRepo.save(orderEntity);
            return ResponseEntity.ok("Заказ успешно создан");
        }
        return new ResponseEntity<String>("Такой пользователь либо товар не существует", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<String> deleteOrder(Long id) {
        if (orderRepo.existsById(id)) {
            orderRepo.deleteById(id);
            return ResponseEntity.ok("Заказ успешно удалён");
        }
        else return new ResponseEntity<String>("Такого заказа не существует", HttpStatus.NOT_FOUND);
    }
}
