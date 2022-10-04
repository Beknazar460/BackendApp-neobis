package com.example.BackendApp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String userName;
    private String userPass;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<OrderEntity> orderEntities;
}
