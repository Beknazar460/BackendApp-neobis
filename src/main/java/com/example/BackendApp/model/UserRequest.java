package com.example.BackendApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String email;
    private String userName;
    private String userPass;
    private String confirmPass;
    private Date dateOfRegister;
    private Role role;
    private Status status;

}
