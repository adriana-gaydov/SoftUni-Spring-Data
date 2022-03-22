package com.example.cardealer.services.impl;

import com.example.cardealer.entities.cars.Car;
import com.example.cardealer.entities.cars.CarAndPartsDTO;
import com.example.cardealer.repositories.CarRepository;
import com.example.cardealer.services.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;
    private ModelMapper mapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelMapper mapper) {
        this.carRepository = carRepository;
        this.mapper = mapper;
    }


    @Override
    public List<Car> getCarsFromMake(String make) {
        return this.carRepository.findByMakeOrderByModelThenByTravelledDistanceDesc(make);
    }

    @Override
    public List<CarAndPartsDTO> getCarsWithTheirListOfParts() {
        return this.carRepository.findAll().stream()
                .map(e -> mapper.map(e, CarAndPartsDTO.class))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
