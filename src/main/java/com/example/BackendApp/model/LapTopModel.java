package com.example.BackendApp.model;

import com.example.BackendApp.entity.LapTopEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LapTopModel {
    private Long id;
    private String title;
    private String price;

    public static LapTopModel toLapTop(LapTopEntity lapTopEntity) {
        LapTopModel lapTopModel = new LapTopModel();
        lapTopModel.setId(lapTopEntity.getId());
        lapTopModel.setTitle(lapTopEntity.getTitle());
        lapTopModel.setPrice(lapTopEntity.getPrice());
        return lapTopModel;
    }
}
