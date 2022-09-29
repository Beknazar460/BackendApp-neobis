package com.example.BackendApp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title_of_product")
    private String titleOfProduct;
    @Column(name = "price_of_product")
    private String priceOfProduct;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "laptops_id")
    private LapTopEntity lapTop;


}
