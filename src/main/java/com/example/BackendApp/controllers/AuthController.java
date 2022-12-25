package com.example.BackendApp.controllers;

import com.example.BackendApp.model.RegistrationRequest;
import com.example.BackendApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v2/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(@ModelAttribute("user") RegistrationRequest registrationRequest) {
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute("user") RegistrationRequest registrationRequest) {
        userService.createUser(registrationRequest);
        return "redirect:/api/v2/auth/login";
    }
}
