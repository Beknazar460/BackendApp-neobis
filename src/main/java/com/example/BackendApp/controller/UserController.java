package com.example.BackendApp.controller;

import com.example.BackendApp.entity.UserEntity;
import com.example.BackendApp.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Tag(
        name = "Контроллер для управления записей пользователя",
        description = "В этом контроллере вы сможете добавлять, удалять, получать, а также обновлять данные пользователей"
)
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение пользователя",
            description = "Позволяет получать пользователя по идентификатору пользователя"
    )
    public ResponseEntity<?> getUserId(@PathVariable
                                           @Parameter(description = "Идентификатор пользователя")
                                                Long id) {
        return userServiceImpl.getUserId(id);
    }

    @GetMapping
    @Operation(
            summary = "Получение всех пользователей",
            description = "Позволяет получить всех пользователей"
    )
    public List<UserEntity> getAllUsers() {
        return userServiceImpl.getAllUsers();
    }

    @PostMapping
    @Operation(
            summary = "Создание пользователя",
            description = "Позволяет создать пользователя введя его данные"
    )
    public ResponseEntity<String> createUser(@RequestBody UserEntity userEntity) {
        return userServiceImpl.createUser(userEntity);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Обновление пользователя",
            description = "Позволяет изменить данные пользователя по идентификатору"
    )
    public ResponseEntity<?> updateUser(@PathVariable
                                            @Parameter(description = "Идентификатор пользователя")
                                                Long id,
                                         @RequestBody UserEntity user) {
        return userServiceImpl.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление пользователя",
            description = "Позволяет удалить пользователя по идентификатору"
    )
    public ResponseEntity deleteUser(@PathVariable
                                         @Parameter(description = "Идентификатор пользователя")
                                            Long id) {
        return userServiceImpl.deleteUser(id);
    }
}
