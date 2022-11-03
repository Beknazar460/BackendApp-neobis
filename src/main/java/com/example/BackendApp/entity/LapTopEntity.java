package com.example.BackendApp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "laptops")
public class LapTopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String price;

        @OneToMany(cascade = CascadeType.ALL, mappedBy = "lapTop")
        private List<OrderEntity> orderEntity;

}
