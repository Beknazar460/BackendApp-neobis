package com.example.BackendApp.service;

import com.example.BackendApp.entity.ToDoEntity;
import com.example.BackendApp.entity.UserEntity;
import com.example.BackendApp.model.ToDoModel;
import com.example.BackendApp.model.UserModel;
import com.example.BackendApp.repository.ToDoRepo;
import com.example.BackendApp.repository.UserRepo;
import com.example.BackendApp.service.impl.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToDoServiceImpl implements ToDoService {
    @Autowired
    ToDoRepo toDoRepo;
    @Autowired
    UserRepo userRepo;

    @Override
    public ToDoModel createToDo(ToDoEntity toDoEntity, Long id) {
        UserEntity userEntity = userRepo.findById(id).get();
        toDoEntity.setUser(userEntity);
        return ToDoModel.toDoModel(toDoRepo.save(toDoEntity));
    }

    @Override
    public ToDoModel updateToDo(Long id) {
        ToDoEntity toDoEntity = toDoRepo.findById(id).get();
        toDoEntity.setCompleted(!toDoEntity.getCompleted());
        return ToDoModel.toDoModel(toDoRepo.save(toDoEntity));
    }
}
