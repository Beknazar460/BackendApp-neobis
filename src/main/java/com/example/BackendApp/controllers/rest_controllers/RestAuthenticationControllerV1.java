package com.example.BackendApp.controllers.rest_controllers;

import com.example.BackendApp.entity.UserEntity;
import com.example.BackendApp.model.AuthenticationRequestModel;
import com.example.BackendApp.model.UserRequest;
import com.example.BackendApp.repository.UserRepo;
import com.example.BackendApp.security.JwtTokenProvider;
import com.example.BackendApp.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class RestAuthenticationControllerV1 {

    private final AuthenticationManager authenticationManager;
    private final UserRepo userRepo;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserServiceImpl userService;

    @Autowired
    public RestAuthenticationControllerV1(AuthenticationManager authenticationManager, UserRepo userRepo, JwtTokenProvider jwtTokenProvider, UserServiceImpl userService) {
        this.authenticationManager = authenticationManager;
        this.userRepo = userRepo;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/registration")
    @Operation(
            summary = "Создание пользователя",
            description = "Позволяет создать пользователя введя его данные"
    )
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PostMapping("/login")
    @Operation(
            summary = "Авторизация пользователя",
            description = "Позволяет авторизовать пользователя"
    )
    private ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestModel requestModel) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestModel.getEmail(),
                                                                                        requestModel.getPassword()));
            UserEntity user = userRepo.findByEmail(requestModel.getEmail());
            if (user == null) {
                throw new UsernameNotFoundException("The user does not exist");
            }
            String token = jwtTokenProvider.createToken(requestModel.getEmail(), user.getRole().name());
            Map<Object, Object> response = new HashMap<>();
            response.put("email", requestModel.getEmail());
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return new ResponseEntity<String>("Invalid mail and password combination", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/logout")
    private void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}
