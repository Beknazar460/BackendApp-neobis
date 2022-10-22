package com.example.BackendApp.service;

import com.example.BackendApp.entity.UserEntity;
import com.example.BackendApp.model.UserModel;
import com.example.BackendApp.model.UserRequest;
import com.example.BackendApp.repository.UserRepo;
import com.example.BackendApp.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public ResponseEntity<String> createUser(UserRequest userRequest) {
        try {
            int nameLength = userRequest.getUserName().length();

            for (int i = 0; i < nameLength; i++) {
                if (userRequest.getUserName().charAt(i) == ' ' || userRequest.getEmail().charAt(i) == ' ') return ResponseEntity.badRequest().body("There should be no spaces in the name or email");

            }
            if (userRepo.findByUserName(userRequest.getUserName()) != null || userRepo.findByEmail(userRequest.getEmail()) != null) {
                return ResponseEntity.badRequest().body("A user with this name or email already exists!");
            }
            else if (!Objects.equals(userRequest.getConfirmPass(), userRequest.getUserPass())) {
                return ResponseEntity.badRequest().body("The repeated password does not match the current password");
            }
            UserEntity user = new UserEntity();
            Date dateOfRegister = new Date();
            user.setDateOfRegister(dateOfRegister);
            user.setEmail(userRequest.getEmail());
            user.setUserName(userRequest.getUserName());
            user.setUserPass(passwordEncoder.encode(userRequest.getUserPass()));
            user.setRole(userRequest.getRole());
            user.setStatus(userRequest.getStatus());
            userRepo.save(user);
            return new ResponseEntity<String>("User is created", HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User isn't created");
        }
    }

    @Override
    public ResponseEntity<?> updateUser(Long id, UserRequest userRequest) {
        return userRepo.findById(id)
                .map(user1 -> {
                    Date dateAfterUpdate = new Date();
                    user1.setUserName(userRequest.getUserName());
                    user1.setEmail(userRequest.getEmail());
                    user1.setDateOfRegister(dateAfterUpdate);
                    user1.setUserPass(passwordEncoder.encode(userRequest.getUserPass()));
                    user1.setRole(userRequest.getRole());
                    user1.setStatus(userRequest.getStatus());
                    userRepo.save(user1);
                    return ResponseEntity.ok("A user with such an ID " + id + " updated");
                }).orElse(new ResponseEntity<String>("A user with such an ID " + id + " not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<?> getUserId(Long id) {
        try {
            UserEntity user = userRepo.findById(id).get();
            return ResponseEntity.ok(UserModel.toUser(user));
        } catch (Exception e) {
            return new ResponseEntity<String>("A user with such an ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<String> deleteUser(Long id) {
        try {
            userRepo.deleteById(id);
            return ResponseEntity.ok("User is deleted");
        } catch (Exception e) {
            return new ResponseEntity<String>("A user with such an ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

}
