package com.example.BackendApp.service;

import com.example.BackendApp.entity.UserEntity;
import com.example.BackendApp.model.UserModel;
import com.example.BackendApp.repository.UserRepo;
import com.example.BackendApp.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public ResponseEntity<String> createUser(UserEntity userEntity) {
        try {
            int nameLength = userEntity.getUserName().length();

            for (int i = 0; i < nameLength; i++) {
                if (userEntity.getUserName().charAt(i) == ' ' || userEntity.getEmail().charAt(i) == ' ') return ResponseEntity.badRequest().body("There should be no spaces in the name or email");

            }
            if (userRepo.findByUserName(userEntity.getUserName()) != null) {
                return ResponseEntity.badRequest().body("A user with this name already exists!");
            }
            else if (!Objects.equals(userEntity.getConfirmPass(), userEntity.getUserPass())) {
                return ResponseEntity.badRequest().body("The repeated password does not match the current password");
            }
            UserEntity user = new UserEntity();
            user.setId(userEntity.getId());
            user.setEmail(userEntity.getEmail());
            user.setUserName(userEntity.getUserName());
            user.setUserPass(passwordEncoder.encode(userEntity.getUserPass()));
            user.setRole(userEntity.getRole());
            user.setStatus(userEntity.getStatus());
            userRepo.save(user);
            return ResponseEntity.ok("User is created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User isn't created");
        }
    }

    @Override
    public ResponseEntity<?> updateUser(Long id, UserEntity userEntity) {
        return userRepo.findById(id)
                .map(user1 -> {
                    user1.setUserName(userEntity.getUserName());
                    user1.setEmail(userEntity.getEmail());
                    user1.setUserPass(passwordEncoder.encode(userEntity.getUserPass()));
                    user1.setRole(userEntity.getRole());
                    user1.setStatus(userEntity.getStatus());
                    userRepo.save(user1);
                    return ResponseEntity.ok("A user with such an ID " + id + " updated");
                }).orElse(new ResponseEntity<String>("A user with such an ID " + id + " not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<?> getUserId(Long id) {
        UserEntity user = userRepo.findById(id).get();
        if (userRepo.existsById(id)) return ResponseEntity.ok(UserModel.toUser(user));
        else return new ResponseEntity<String>("A user with such an ID " + id + " not found", HttpStatus.NOT_FOUND);
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
