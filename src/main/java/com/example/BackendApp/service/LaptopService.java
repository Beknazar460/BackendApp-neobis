package com.example.BackendApp.service;

import com.example.BackendApp.entity.LaptopEntity;
import com.example.BackendApp.model.LaptopModel;
import com.example.BackendApp.repository.LaptopRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LaptopService {

    private final LaptopRepo laptopRepo;

    public ResponseEntity<String> createLapTop(LaptopModel lapTopModel) {
        try {
            if (laptopRepo.findByTitle(lapTopModel.getTitle()).isPresent()) {
                return ResponseEntity.badRequest().body("The product already exists");
            }
            LaptopEntity lapTopEntity = new LaptopEntity(lapTopModel.getTitle(), lapTopModel.getPrice());
            laptopRepo.save(lapTopEntity);
            return new ResponseEntity<String>("Product is created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>("Product isn't created", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> updateLapTop(Long id, LaptopModel lapTopModel) {
            return laptopRepo.findById(id)
                    .map(lapTopEntity -> {
                        lapTopEntity.setTitle(lapTopModel.getTitle());
                        lapTopEntity.setPrice(lapTopModel.getPrice());
                        laptopRepo.save(lapTopEntity);
                        return ResponseEntity.ok("Product with this id " + id + " updated");
                    }).orElse(new ResponseEntity<String>("Product with this id " + id + " not found", HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<?> getLapTopId(Long id) {
            if (laptopRepo.findById(id).isPresent()) {
                return ResponseEntity.ok(LaptopModel.toLapTop(laptopRepo.findById(id).get()));
            }
            else {
                return new ResponseEntity<String>("Product with this id " + id + " not found", HttpStatus.NOT_FOUND);
            }
    }

    public List<LaptopEntity> getALlLapTops() {
        return laptopRepo.findAll();
    }

    public ResponseEntity<String> deleteLapTop(Long id) {
        if (laptopRepo.existsById(id)) {
            laptopRepo.deleteById(id);
            return ResponseEntity.ok("Laptop is deleted");
        }
        else return new ResponseEntity<String>("There is no such Laptop", HttpStatus.NOT_FOUND);
    }
}
