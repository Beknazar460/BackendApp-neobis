package com.example.BackendApp.repository;

import com.example.BackendApp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserName(String userName);
}
