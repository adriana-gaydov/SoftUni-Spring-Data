package com.example.cardealer.services;

import com.example.cardealer.entities.cars.Car;
import com.example.cardealer.entities.cars.CarAndPartsDTO;

import java.util.List;

public interface CarService {

    List<Car> getCarsFromMake(String make);
    List<CarAndPartsDTO> getCarsWithTheirListOfParts();
}
