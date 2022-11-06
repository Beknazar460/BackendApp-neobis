package com.example.BackendApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String email;
    private String userName;
    private String userPass;
    private String confirmPass;

}
