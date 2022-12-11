package com.example.BackendApp.service;

import com.example.BackendApp.entity.UserEntity;
import com.example.BackendApp.model.*;
import com.example.BackendApp.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    public ResponseEntity<String> createUser(RegistrationRequest registrationRequest) {
        try {
            int nameLength = registrationRequest.getUserName().length();
            int emailLength = registrationRequest.getEmail().length();

            for (int i = 0; i < nameLength; i++) {
                if (registrationRequest.getUserName().charAt(i) == ' ')
                    return ResponseEntity.badRequest().body("There should be no spaces in the name");
            }
            for (int i = 0; i < emailLength; i++) {
                if (registrationRequest.getEmail().charAt(i) == ' ') {
                    return ResponseEntity.badRequest().body("There should be no spaces in the email");
                }
            }
            if (userRepo.findByEmail(registrationRequest.getEmail()) != null) {
                return ResponseEntity.badRequest().body("A user with this email already exists!");
            }
            if (!Objects.equals(registrationRequest.getConfirmPass(), registrationRequest.getUserPass())) {
                return ResponseEntity.badRequest().body("The repeated password does not match the current password");
            }
            UserEntity user = new UserEntity(registrationRequest.getEmail(), registrationRequest.getUserName(), passwordEncoder.encode(registrationRequest.getUserPass()),
                    LocalDateTime.now(), Role.USER, Status.ACTIVE);
            userRepo.save(user);
            return new ResponseEntity<String>("User is created", HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User isn't created");
        }
    }

    public ResponseEntity<?> updateUser(Long id, RegistrationRequest registrationRequest) {
        return userRepo.findById(id)
                .map(user1 -> {
                    user1.setUserName(registrationRequest.getUserName());
                    user1.setEmail(registrationRequest.getEmail());
                    user1.setDateOfRegister(LocalDateTime.now());
                    user1.setUserPass(passwordEncoder.encode(registrationRequest.getUserPass()));
                    user1.setRole(Role.USER);
                    user1.setStatus(Status.ACTIVE);
                    userRepo.save(user1);
                    return ResponseEntity.ok("A user with such an ID " + id + " updated");
                }).orElse(new ResponseEntity<String>("A user with such an ID " + id + " not found", HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<?> getUserId(Long id) {
        if (userRepo.findById(id).isPresent()) {
            return ResponseEntity.ok(UserModel.toUser(userRepo.findById(id).get()));
        } else {
            return new ResponseEntity<String>("A user with such an ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> deleteUser(Long id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return ResponseEntity.ok("User is deleted");
        }
        else return new ResponseEntity<String>("There is no such user", HttpStatus.NOT_FOUND);
    }
}
