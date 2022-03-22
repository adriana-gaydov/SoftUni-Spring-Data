package com.example.cardealer.services.impl;

import com.example.cardealer.entities.cars.Car;
import com.example.cardealer.entities.cars.CarSeedDTO;
import com.example.cardealer.entities.customers.Customer;
import com.example.cardealer.entities.customers.CustomerSeedDTO;
import com.example.cardealer.entities.parts.Part;
import com.example.cardealer.entities.parts.PartSeedDTO;
import com.example.cardealer.entities.sales.Sale;
import com.example.cardealer.entities.suppliers.Supplier;
import com.example.cardealer.entities.suppliers.SupplierSeedDTO;
import com.example.cardealer.repositories.*;
import com.example.cardealer.services.SeedService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

@Service
public class SeedServiceImpl implements SeedService {

    private final SupplierRepository supplierRepository;
    private final PartRepository partRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;
    private final SaleRepository saleRepository;
    private final ModelMapper mapper;
    private final Gson gson;

    @Autowired
    public SeedServiceImpl(SupplierRepository supplierRepository, PartRepository partRepository, CustomerRepository customerRepository, CarRepository carRepository, SaleRepository saleRepository, ModelMapper mapper, Gson gson) {
        this.supplierRepository = supplierRepository;
        this.partRepository = partRepository;
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
        this.saleRepository = saleRepository;
        this.mapper = mapper;
        this.gson = gson;
    }


    @Override
    public void seedSuppliers() throws FileNotFoundException {
        Arrays.stream(this.gson.fromJson(new FileReader(SUPPLIERS_PATH), SupplierSeedDTO[].class))
                .map(e -> mapper.map(e, Supplier.class))
                .forEach(this.supplierRepository::save);
    }

    @Override
    public void seedParts() throws FileNotFoundException {
        Arrays.stream(this.gson.fromJson(new FileReader(PARTS_PATH), PartSeedDTO[].class))
                .map(e -> mapper.map(e, Part.class))
                .forEach(p -> {
                    setRandomSupplier(p);
                    this.partRepository.save(p);
                });
    }

    private void setRandomSupplier(Part p) {
        long suppliersCount = this.supplierRepository.count();

        long supplierId = new Random().nextLong(suppliersCount) + 1;

        Supplier supplier = this.supplierRepository.getById(supplierId);

        p.setSupplier(supplier);
    }

    @Override
    public void seedCars() throws FileNotFoundException {
        Arrays.stream(this.gson.fromJson(new FileReader(CARS_PATH), CarSeedDTO[].class))
                .map(e -> mapper.map(e, Car.class))
                .forEach(c -> {
                    setRandomParts(c);
                    this.carRepository.save(c);
                });
    }

    private void setRandomParts(Car c) {
        long partsCount = this.partRepository.count();

        int neededPartsCount = new Random().nextInt(2) + 3;

        Set<Part> partsToAdd = new HashSet<>();

        while (partsToAdd.size() < neededPartsCount) {
            long partId = new Random().nextLong(partsCount) + 1;
            Part part = this.partRepository.getById(partId);

            partsToAdd.add(part);
        }

        c.setParts(partsToAdd);
    }

    @Override
    public void seedCustomers() throws FileNotFoundException {
        Arrays.stream(this.gson.fromJson(new FileReader(CUSTOMERS_PATH), CustomerSeedDTO[].class))
                .map(e -> mapper.map(e, Customer.class))
                .forEach(this.customerRepository::save);
    }

    @Override
    public void seedSales() {
        long customerCount = this.customerRepository.count();
        int discountCount = DISCOUNTS.length;


        for (int i = 0; i < 60; i++) {
            Car car = this.carRepository.getById((long) i + 1);

            long customerId = new Random().nextLong(customerCount) + 1;
            Customer customer = this.customerRepository.getById(customerId);

            int discount = DISCOUNTS[new Random().nextInt(discountCount)];

            if (customer.isYoungDriver()) {
                discount -= 5;
            }

            if (discount < 0) {
                discount = 0;
            }


            this.saleRepository.save(new Sale(car, customer, discount));
        }
    }
}
