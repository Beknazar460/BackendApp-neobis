package com.example.BackendApp.model;

import lombok.Data;

@Data
public class AuthenticationRequestModel {

    private String email;
    private String password;

}
