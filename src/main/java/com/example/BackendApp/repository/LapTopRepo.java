package com.example.BackendApp.repository;

import com.example.BackendApp.entity.LapTopEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LapTopRepo extends JpaRepository<LapTopEntity, Long> {

    LapTopEntity findByTitle(String name);

}
