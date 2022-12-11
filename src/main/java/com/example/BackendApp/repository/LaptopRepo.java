package com.example.BackendApp.repository;

import com.example.BackendApp.entity.LaptopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LaptopRepo extends JpaRepository<LaptopEntity, Long> {
    Optional<LaptopEntity> findByTitle(String name);
}
