package com.example.BackendApp.service;

import com.example.BackendApp.entity.LapTopEntity;
import com.example.BackendApp.model.LapTopModel;
import com.example.BackendApp.repository.LapTopRepo;
import com.example.BackendApp.service.impl.LapTopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
                return ResponseEntity.badRequest().body("Товар уже существует");
            }
            lapTopEntity.setTitle(lapTopModel.getTitle());
            lapTopEntity.setPrice(lapTopModel.getPrice());
            lapTopRepo.save(lapTopEntity);
            return ResponseEntity.ok("Товар успешно создан");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Товар не создан");
        }
    }

    @Override
    public ResponseEntity<?> updateLapTop(Long id, LapTopModel lapTopModel) {
            return lapTopRepo.findById(id)
                    .map(lapTopEntity -> {
                        lapTopEntity.setTitle(lapTopModel.getTitle());
                        lapTopEntity.setPrice(lapTopModel.getPrice());
                        lapTopRepo.save(lapTopEntity);
                        return ResponseEntity.ok("Товар с таким айди " + id + " обновлен");
                    }).orElse(new ResponseEntity<String>("Товар с таким айди " + id + " не найден", HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<?> getLapTopId(Long id) {
            LapTopEntity lapTopEntity = lapTopRepo.findById(id).get();
            if (lapTopRepo.existsById(id)) return ResponseEntity.ok(LapTopModel.toLapTop(lapTopEntity));
            else return new ResponseEntity<String>("Товар с таким айди " + id + " не найден", HttpStatus.NOT_FOUND);
    }

    @Override
    public List<LapTopEntity> getALlLapTops() {
        return lapTopRepo.findAll();
    }

    @Override
    public ResponseEntity<String> deleteLapTop(Long id) {
        try {
            lapTopRepo.deleteById(id);
            return ResponseEntity.ok("Товар удален");
        } catch (Exception e) {
            return new ResponseEntity<String>("Товар не найден", HttpStatus.NOT_FOUND);
        }
    }
}
