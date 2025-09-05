package com.klu.motorcycle.service;

import java.util.List;
import com.klu.motorcycle.entity.Motorcycle;

public interface MotorcycleService {
    Motorcycle addMotorcycle(Motorcycle motorcycle);
    List<Motorcycle> getAllMotorcycles();
    Motorcycle getMotorcycleById(int id);
    Motorcycle updateMotorcycle(Motorcycle motorcycle);
    void deleteMotorcycleById(int id);
}
