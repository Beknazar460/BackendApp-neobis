package com.example.BackendApp.model;

import com.example.BackendApp.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UserModel {

    private Long id;
    private String userName;
    private List<ToDoModel> toDoModels;

    public static UserModel toModel(UserEntity userEntity) {
        UserModel userModel = new UserModel();
        userModel.setUserName(userEntity.getUserName());
        userModel.setId(userEntity.getId());
        userModel.setToDoModels(userEntity.getToDoEntities().stream().map(ToDoModel::toDoModel).collect(Collectors.toList()));
        return userModel;
    }
}
