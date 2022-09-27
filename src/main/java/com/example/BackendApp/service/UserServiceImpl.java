package com.example.BackendApp.service;

import com.example.BackendApp.entity.UserEntity;
import com.example.BackendApp.exceptions.UserAlreadyExistException;
import com.example.BackendApp.exceptions.UserNotFoundException;
import com.example.BackendApp.model.UserModel;
import com.example.BackendApp.repository.UserRepo;
import com.example.BackendApp.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public void createUser(UserEntity user) throws UserAlreadyExistException {
        int nameLength = user.getUserName().length();

        for (int i = 0; i < nameLength; i++) {
            if (user.getUserName().charAt(i) == ' ') throw new UserAlreadyExistException("В имени не должно присутствовать пробелов");
        }
        if (userRepo.findByUserName(user.getUserName()) != null) {
            throw new UserAlreadyExistException("Пользователь с таким именем уже существует!");
        }
        userRepo.save(user);
    }

    @Override
    public UserModel getUserId(Long id) throws UserNotFoundException {
        UserEntity userEntity = userRepo.findById(id).get();
        if (userEntity == null) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        return UserModel.toModel(userEntity);
    }

    @Override
    public UserModel getUserName(String name) throws UserNotFoundException {
        UserEntity userEntity = userRepo.findByUserName(name);
        if (userEntity == null) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        return UserModel.toModel(userEntity);
    }

    @Override
    public Long deleteUser(Long id) {
        userRepo.deleteById(id);
        return id;
    }

}
