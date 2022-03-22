package com.example.cardealer.entities.sales;

import com.example.cardealer.entities.cars.Car;
import com.example.cardealer.entities.cars.CarExportDTO;

public class SaleExportDTO {

    private Long id;
    private int discountPercentage;
    private CarExportDTO car;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public CarExportDTO getCar() {
        return car;
    }

    public void setCar(CarExportDTO car) {
        this.car = car;
    }
}
