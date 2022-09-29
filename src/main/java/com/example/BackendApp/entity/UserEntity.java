package com.example.BackendApp.entity;

import com.example.BackendApp.model.Role;
import com.example.BackendApp.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String userName;
    private String userPass;
    private Role role;
    private Status status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<OrderEntity> orderEntities;
}
