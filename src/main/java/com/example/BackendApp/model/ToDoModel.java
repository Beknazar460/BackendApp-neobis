package com.example.BackendApp.model;

import com.example.BackendApp.entity.ToDoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ToDoModel {
    private Long id;
    private String title;
    private Boolean completed;

    public static ToDoModel toDoModel(ToDoEntity toDoEntity) {
        ToDoModel toDoModel = new ToDoModel();
        toDoModel.setId(toDoEntity.getId());
        toDoModel.setTitle(toDoEntity.getTitle());
        toDoModel.setCompleted(toDoEntity.getCompleted());
        return toDoModel;
    }
}
