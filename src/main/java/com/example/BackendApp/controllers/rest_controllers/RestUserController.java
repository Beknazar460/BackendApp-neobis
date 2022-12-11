package com.example.BackendApp.controllers.rest_controllers;

import com.example.BackendApp.entity.UserEntity;
import com.example.BackendApp.model.UserRequest;
import com.example.BackendApp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag (
        name = "Контроллер для управления записей пользователя",
        description = "В этом контроллере вы сможете добавлять, удалять, получать, а также обновлять данные пользователей"
)
public class RestUserController {

    private final UserService userService;

    @Autowired
    public RestUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение пользователя",
            description = "Позволяет получать пользователя по идентификатору пользователя"
    )
    @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<?> getUserId(@PathVariable
                                           @Parameter(description = "Идентификатор пользователя")
                                                Long id) {
        return userService.getUserId(id);
    }

    @GetMapping
    @Operation(
            summary = "Получение всех пользователей",
            description = "Позволяет получить всех пользователей"
    )
    @PreAuthorize("hasAuthority('users:read')")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    @Operation(
            summary = "Создание пользователя",
            description = "Позволяет создать пользователя введя его данные"
    )
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Обновление пользователя",
            description = "Позволяет изменить данные пользователя по идентификатору"
    )
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<?> updateUser(@PathVariable
                                            @Parameter(description = "Идентификатор пользователя")
                                                Long id,
                                         @RequestBody UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление пользователя",
            description = "Позволяет удалить пользователя по идентификатору"
    )
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<?> deleteUser(@PathVariable
                                            @Parameter(description = "Идентификатор пользователя")
                                                Long id) {
        return userService.deleteUser(id);
    }
}
