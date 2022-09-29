package com.example.BackendApp.service.impl;

import com.example.BackendApp.entity.LapTopEntity;
import com.example.BackendApp.exceptions.LapTopNotFoundException;
import com.example.BackendApp.model.LapTopModel;

public interface LapTopService {

    void createLapTop(LapTopEntity lapTopEntity);
    LapTopModel updateLapTop(Long id, LapTopEntity lapTopEntity);
    LapTopModel getLapTopId(Long id) throws LapTopNotFoundException;
    LapTopModel getLapTopTitle(String name) throws LapTopNotFoundException;
    Long deleteLapTop(Long id) throws LapTopNotFoundException;
}
