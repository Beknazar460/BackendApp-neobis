package com.example.BackendApp.model;

import lombok.Getter;

@Getter
public class RegistrationRequest {
    private String email;
    private String userName;
    private String userPass;
    private String confirmPass;
}
