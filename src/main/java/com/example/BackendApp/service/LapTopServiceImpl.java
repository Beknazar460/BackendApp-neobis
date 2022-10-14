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
                return ResponseEntity.badRequest().body("The product already exists");
            }
            lapTopEntity.setTitle(lapTopModel.getTitle());
            lapTopEntity.setPrice(lapTopModel.getPrice());
            lapTopRepo.save(lapTopEntity);
            return ResponseEntity.ok("Product is created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Product isn't created");
        }
    }

    @Override
    public ResponseEntity<?> updateLapTop(Long id, LapTopModel lapTopModel) {
            return lapTopRepo.findById(id)
                    .map(lapTopEntity -> {
                        lapTopEntity.setTitle(lapTopModel.getTitle());
                        lapTopEntity.setPrice(lapTopModel.getPrice());
                        lapTopRepo.save(lapTopEntity);
                        return ResponseEntity.ok("Product with this id " + id + " updated");
                    }).orElse(new ResponseEntity<String>("Product with this id " + id + " not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<?> getLapTopId(Long id) {
            LapTopEntity lapTopEntity = lapTopRepo.findById(id).get();
            if (lapTopRepo.existsById(id)) return ResponseEntity.ok(LapTopModel.toLapTop(lapTopEntity));
            else return new ResponseEntity<String>("Product with this id " + id + " not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public List<LapTopEntity> getALlLapTops() {
        return lapTopRepo.findAll();
    }

    @Override
    public ResponseEntity<String> deleteLapTop(Long id) {
        try {
            lapTopRepo.deleteById(id);
            return ResponseEntity.ok("Product is deleted");
        } catch (Exception e) {
            return new ResponseEntity<String>("Product not found", HttpStatus.NOT_FOUND);
        }
    }
}
