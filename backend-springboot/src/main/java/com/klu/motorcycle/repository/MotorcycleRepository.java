package com.klu.motorcycle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.klu.motorcycle.entity.Motorcycle;

@Repository
public interface MotorcycleRepository extends JpaRepository<Motorcycle, Integer> {

    // Custom query methods (example)
    Motorcycle findByMake(String make);
    Motorcycle findByModel(String model);
    Motorcycle findByColor(String color);
}
