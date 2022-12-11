package com.example.BackendApp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@Table(name = "laptops")
public class LaptopEntity {

    public LaptopEntity(String title, String price) {
        this.title = title;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String price;

        @OneToMany(cascade = CascadeType.ALL, mappedBy = "lapTop")
        private List<OrderEntity> orderEntity;

}
