package com.klu.motorcycle.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "motorcycle_table")
public class Motorcycle {

    @Id
    @Column(name = "motorcycle_id")
    private int id;

    @Column(name = "motorcycle_make", nullable = false, length = 50)
    private String make;

    @Column(name = "motorcycle_model", nullable = false, length = 50)
    private String model;

    @Column(name = "motorcycle_year", nullable = false)
    private int year;

    @Column(name = "motorcycle_engine_type", nullable = false, length = 20)
    private String engineType;

    @Column(name = "motorcycle_color", nullable = false, length = 20)
    private String color;

    @Column(name = "motorcycle_price", nullable = false)
    private double price;

    @Column(name = "motorcycle_mileage", nullable = false)
    private double mileage;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    @Override
    public String toString() {
        return "Motorcycle [id=" + id + ", make=" + make + ", model=" + model + ", year=" + year
                + ", engineType=" + engineType + ", color=" + color + ", price=" + price + ", mileage=" + mileage + "]";
    }
}
