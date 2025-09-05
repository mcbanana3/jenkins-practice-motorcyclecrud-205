package com.klu.motorcycle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.klu.motorcycle.entity.Motorcycle;
import com.klu.motorcycle.service.MotorcycleService;

@RestController
@RequestMapping("/motorcycleapi/")
@CrossOrigin(origins = "*")
public class MotorcycleController {

    @Autowired
    private MotorcycleService motorcycleService;
    
    @GetMapping("/")
    public String home() {
        return "Motorcycle Management API";
    }

    @PostMapping("/add")
    public ResponseEntity<Motorcycle> addMotorcycle(@RequestBody Motorcycle motorcycle) {
        Motorcycle savedMotorcycle = motorcycleService.addMotorcycle(motorcycle);
        return new ResponseEntity<>(savedMotorcycle, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Motorcycle>> getAllMotorcycles() {
        List<Motorcycle> motorcycles = motorcycleService.getAllMotorcycles();
        return new ResponseEntity<>(motorcycles, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getMotorcycleById(@PathVariable int id) {
        Motorcycle motorcycle = motorcycleService.getMotorcycleById(id);
        if (motorcycle != null) {
            return new ResponseEntity<>(motorcycle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Motorcycle with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateMotorcycle(@RequestBody Motorcycle motorcycle) {
        Motorcycle existing = motorcycleService.getMotorcycleById(motorcycle.getId());
        if (existing != null) {
            Motorcycle updatedMotorcycle = motorcycleService.updateMotorcycle(motorcycle);
            return new ResponseEntity<>(updatedMotorcycle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot update. Motorcycle with ID " + motorcycle.getId() + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMotorcycle(@PathVariable int id) {
        Motorcycle existing = motorcycleService.getMotorcycleById(id);
        if (existing != null) {
            motorcycleService.deleteMotorcycleById(id);
            return new ResponseEntity<>("Motorcycle with ID " + id + " deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot delete. Motorcycle with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}
