package com.example.BackendApp.model;

import com.example.BackendApp.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderModel {
    private Long id;
    private String titleOfProduct;
    private String priceOfProduct;
    private Long usersId;
    private Long laptopsId;

    public static OrderModel orderModel(OrderEntity orderEntity) {
        OrderModel orderModel = new OrderModel();
        orderModel.setId(orderEntity.getId());
        orderModel.setTitleOfProduct(orderEntity.getTitleOfProduct());
        orderModel.setPriceOfProduct(orderEntity.getPriceOfProduct());
        orderModel.setUsersId(orderEntity.getUser().getId());
        orderModel.setLaptopsId(orderEntity.getLapTop().getId());
        return orderModel;
    }
}
