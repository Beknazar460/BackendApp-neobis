package com.example.BackendApp.service;

import com.example.BackendApp.entity.UserEntity;
import com.example.BackendApp.model.UserModel;
import com.example.BackendApp.repository.UserRepo;
import com.example.BackendApp.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
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
                if (userEntity.getUserName().charAt(i) == ' ') return ResponseEntity.badRequest().body("В имени не должно присутствовать пробелов");
            }
            if (userRepo.findByUserName(userEntity.getUserName()) != null) {
                return ResponseEntity.badRequest().body("Пользователь с таким именем уже существует!");
            }
            userRepo.save(userEntity);
            return ResponseEntity.ok("Пользователь успешно создан");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Пользователь не создан");
        }
    }

    @Override
    public ResponseEntity<?> updateUser(Long id, UserEntity userEntity) {
        return userRepo.findById(id)
                .map(user1 -> {
                    user1.setUserName(userEntity.getUserName());
                    user1.setEmail(userEntity.getEmail());
                    user1.setUserPass(userEntity.getUserPass());
                    userRepo.save(user1);
                    return ResponseEntity.ok("Пользователь с таким айди " + id + " обновлен");
                }).orElse(new ResponseEntity<String>("Пользователь с таким айди " + id + " не найден", HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<?> getUserId(Long id) {
        UserEntity user = userRepo.findById(id).get();
        if (userRepo.existsById(id)) return ResponseEntity.ok(UserModel.toUser(user));
        else return ResponseEntity.badRequest().body("Пользователь по айди " + id + " не найден");
    }

    @Override
    public ResponseEntity<String> deleteUser(Long id) {
        try {
            userRepo.deleteById(id);
            return ResponseEntity.ok("Пользователь удалён");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Пользователь с таким айди " + id + " не найден");
        }
    }

}
