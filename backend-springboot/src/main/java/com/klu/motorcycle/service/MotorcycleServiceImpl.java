package com.klu.motorcycle.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.motorcycle.entity.Motorcycle;
import com.klu.motorcycle.repository.MotorcycleRepository;

@Service
public class MotorcycleServiceImpl implements MotorcycleService {

    @Autowired
    private MotorcycleRepository motorcycleRepository;

    @Override
    public Motorcycle addMotorcycle(Motorcycle motorcycle) {
        return motorcycleRepository.save(motorcycle);
    }

    @Override
    public List<Motorcycle> getAllMotorcycles() {
        return motorcycleRepository.findAll();
    }

    @Override
    public Motorcycle getMotorcycleById(int id) {
        Optional<Motorcycle> opt = motorcycleRepository.findById(id);
        return opt.orElse(null);
    }

    @Override
    public Motorcycle updateMotorcycle(Motorcycle motorcycle) {
        return motorcycleRepository.save(motorcycle);
    }

    @Override
    public void deleteMotorcycleById(int id) {
        motorcycleRepository.deleteById(id);
    }
}
