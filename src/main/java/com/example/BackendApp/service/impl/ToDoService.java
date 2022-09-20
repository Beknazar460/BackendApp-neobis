package com.example.BackendApp.service.impl;

import com.example.BackendApp.entity.ToDoEntity;
import com.example.BackendApp.model.ToDoModel;

public interface ToDoService {
    ToDoModel createToDo(ToDoEntity toDoEntity, Long id);
    ToDoModel updateToDo(Long id);
}
