package com.example.BackendApp.service.impl;

import com.example.BackendApp.model.LapTopModel;
import org.springframework.http.ResponseEntity;

public interface LapTopService {

    ResponseEntity<?> createLapTop(LapTopModel lapTopModel);
    ResponseEntity<?> updateLapTop(Long id, LapTopModel lapTopModel);
    ResponseEntity<?> getLapTopId(Long id);
    ResponseEntity<String> deleteLapTop(Long id);
}
