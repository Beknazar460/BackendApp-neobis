package com.example.BackendApp.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class OrderEntity {

    public OrderEntity(String titleOfProduct, String priceOfProduct, UserEntity user) {
        this.titleOfProduct = titleOfProduct;
        this.priceOfProduct = priceOfProduct;
        this.user = user;
    }

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
    private LaptopEntity lapTop;

}
