package com.example.BackendApp.model;

import com.example.BackendApp.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserModel {

    private Long id;
    private String userName;
    private String email;
    private LocalDateTime dateOfRegister;
    private Role role;
    private Status status;
    private List<OrderModel> toOrders;

    public static UserModel toUser(UserEntity userEntity) {
        UserModel userModel = new UserModel();
        userModel.setEmail(userEntity.getEmail());
        userModel.setDateOfRegister(userEntity.getDateOfRegister());
        userModel.setUserName(userEntity.getUserName());
        userModel.setId(userEntity.getId());
        userModel.setRole(userEntity.getRole());
        userModel.setStatus(userEntity.getStatus());
        userModel.setToOrders(userEntity.getOrderEntities().stream().map(OrderModel::orderModel).collect(Collectors.toList()));
        return userModel;
    }
}
