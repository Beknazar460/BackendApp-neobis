package com.example.BackendApp.service;

import com.example.BackendApp.entity.LapTopEntity;
import com.example.BackendApp.exceptions.LapTopAlreadyExistException;
import com.example.BackendApp.exceptions.LapTopNotFoundException;
import com.example.BackendApp.model.LapTopModel;
import com.example.BackendApp.repository.LapTopRepo;
import com.example.BackendApp.service.impl.LapTopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LapTopServiceImpl implements LapTopService {

    @Autowired
    LapTopRepo lapTopRepo;

    @Override
    public void createLapTop(LapTopEntity lapTopEntity) {
        lapTopRepo.save(lapTopEntity);
    }

    @Override
    public LapTopModel updateLapTop(Long id, LapTopEntity lapTopEntity) {
        return null;
    }

    @Override
    public LapTopModel getLapTopId(Long id) throws LapTopNotFoundException {
        LapTopEntity lapTopEntity = lapTopRepo.findById(id).get();
        if (lapTopEntity == null) {
            throw new LapTopNotFoundException("Товар не найден");
        }
        return LapTopModel.toLapTop(lapTopEntity);
    }

    @Override
    public LapTopModel getLapTopTitle(String name) throws LapTopNotFoundException {
        LapTopEntity lapTopEntity = lapTopRepo.findByTitle(name);
        if (lapTopEntity == null) {
            throw new LapTopNotFoundException("Товар не найден");
        }
        return LapTopModel.toLapTop(lapTopEntity);
    }

    @Override
    public Long deleteLapTop(Long id) throws LapTopNotFoundException{
        if (lapTopRepo.findById(id).get() == null) {
            throw new LapTopNotFoundException("Товар не найден");
        }
        lapTopRepo.deleteById(id);
        return id;
    }
}
