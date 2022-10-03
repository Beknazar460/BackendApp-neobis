package com.example.BackendApp.service;

import com.example.BackendApp.entity.LapTopEntity;
import com.example.BackendApp.model.LapTopModel;
import com.example.BackendApp.repository.LapTopRepo;
import com.example.BackendApp.service.impl.LapTopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LapTopServiceImpl implements LapTopService {


    private final LapTopRepo lapTopRepo;

    @Autowired
    public LapTopServiceImpl(LapTopRepo lapTopRepo) {
        this.lapTopRepo = lapTopRepo;
    }

    @Override
    public ResponseEntity<?> createLapTop(LapTopModel lapTopModel) {
        try {
            LapTopEntity lapTopEntity = new LapTopEntity();
            if (lapTopRepo.findByTitle(lapTopModel.getTitle()) != null) {
                return new ResponseEntity<String>("Товар уже существует", HttpStatus.BAD_REQUEST);
            }
            lapTopEntity.setTitle(lapTopModel.getTitle());
            lapTopEntity.setPrice(lapTopModel.getPrice());
            lapTopRepo.save(lapTopEntity);
            return new ResponseEntity<String>("Товар успешно создан", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Товар не создан", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> updateLapTop(Long id, LapTopModel lapTopModel) {
            return lapTopRepo.findById(id)
                    .map(lapTopEntity -> {
                        lapTopEntity.setTitle(lapTopModel.getTitle());
                        lapTopEntity.setPrice(lapTopModel.getPrice());
                        lapTopRepo.save(lapTopEntity);
                        return new ResponseEntity<String>("Товар с таким айди " + id + " обновлен", HttpStatus.OK);
                    }).orElse(new ResponseEntity<String>("Товар с таким айди " + id + " не найден", HttpStatus.BAD_REQUEST));
    }

    @Override
    public ResponseEntity<?> getLapTopId(Long id) {
        LapTopEntity lapTopEntity = lapTopRepo.findById(id).get();
        if (lapTopRepo.existsById(id)) return new ResponseEntity<LapTopModel>(LapTopModel.toLapTop(lapTopEntity), HttpStatus.OK);
        else return new ResponseEntity<String>("Товар с таким айди " + id + " не найден", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> deleteLapTop(Long id) {
        try {
            lapTopRepo.deleteById(id);
            return new ResponseEntity<String>("Товар удален", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Товар не найден", HttpStatus.BAD_REQUEST);
        }
    }
}
