package com.example.BackendApp.service;

import com.example.BackendApp.entity.UserEntity;
import com.example.BackendApp.exceptions.UserNotFoundException;
import com.example.BackendApp.repository.UserRepo;
import com.example.BackendApp.security.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;

    @Autowired
    public UserDetailsServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserEntity user = userRepo.findByEmail(email);
        if (user == null) {
            try {
                throw new UserNotFoundException("Польззователь не найден");
            } catch (UserNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return UserSecurity.fromUser(user);
    }
}
