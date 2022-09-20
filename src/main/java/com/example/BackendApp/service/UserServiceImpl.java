package com.example.BackendApp.service;

import com.example.BackendApp.entity.UserEntity;
import com.example.BackendApp.exceptions.UserAlreadyExistException;
import com.example.BackendApp.exceptions.UserNotFoundException;
import com.example.BackendApp.model.UserModel;
import com.example.BackendApp.repository.UserRepo;
import com.example.BackendApp.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;


    @Override
    public void createUser(UserEntity user) throws UserAlreadyExistException {
        if (userRepo.findByUserName(user.getUserName()) != null) {
            throw new UserAlreadyExistException("Пользователь с таким именем уже существует!");
        }
        userRepo.save(user);
    }

    @Override
    public UserModel getOneUser(Long id) throws UserNotFoundException {
        UserEntity userEntity = userRepo.findById(id).get();
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
