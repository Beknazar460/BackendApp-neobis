package com.example.BackendApp.model;

import com.example.BackendApp.entity.LaptopEntity;
import lombok.*;

@Data
public class LaptopModel {
    private Long id;
    private String title;
    private String price;

    public static LaptopModel toLapTop(LaptopEntity lapTopEntity) {
        LaptopModel lapTopModel = new LaptopModel();
        lapTopModel.setId(lapTopEntity.getId());
        lapTopModel.setTitle(lapTopEntity.getTitle());
        lapTopModel.setPrice(lapTopEntity.getPrice());
        return lapTopModel;
    }
}
