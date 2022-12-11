package com.example.BackendApp.entity;

import com.example.BackendApp.model.Role;
import com.example.BackendApp.model.Status;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {

    public UserEntity(String email, String userName, String userPass, LocalDateTime dateOfRegister, Role role, Status status) {
        this.email = email;
        this.userName = userName;
        this.userPass = userPass;
        this.dateOfRegister = dateOfRegister;
        this.role = role;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String userName;
    private String userPass;
    private LocalDateTime dateOfRegister;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<OrderEntity> orderEntities;

}
