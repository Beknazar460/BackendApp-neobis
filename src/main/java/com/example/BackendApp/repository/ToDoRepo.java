package com.example.BackendApp.repository;

import com.example.BackendApp.entity.ToDoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepo extends JpaRepository<ToDoEntity, Long> {
}
