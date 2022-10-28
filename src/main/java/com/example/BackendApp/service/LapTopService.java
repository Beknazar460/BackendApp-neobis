package com.example.BackendApp.service;

import com.example.BackendApp.entity.LapTopEntity;
import com.example.BackendApp.model.LapTopModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LapTopService {

    List<LapTopEntity> getALlLapTops();
    ResponseEntity<?> createLapTop(LapTopModel lapTopModel);
    ResponseEntity<?> updateLapTop(Long id, LapTopModel lapTopModel);
    ResponseEntity<?> getLapTopId(Long id);
    ResponseEntity<String> deleteLapTop(Long id);
}
