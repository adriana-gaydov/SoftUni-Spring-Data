package com.example.cardealer.services;

import java.io.FileNotFoundException;

public interface SeedService {
    String SUPPLIERS_PATH = "src/main/resources/suppliers.json";
    String PARTS_PATH = "src/main/resources/parts.json";
    String CARS_PATH = "src/main/resources/cars.json";
    String CUSTOMERS_PATH = "src/main/resources/customers.json";
    int[] DISCOUNTS = new int[] {0, 5, 10, 15, 20, 30, 40, 50};

    void seedSuppliers() throws FileNotFoundException;
    void seedParts() throws FileNotFoundException;
    void seedCars() throws FileNotFoundException;
    void seedCustomers() throws FileNotFoundException;
    void seedSales() throws FileNotFoundException;

    default void seedDatabase() throws FileNotFoundException {
        seedSuppliers();
        seedParts();
        seedCars();
        seedCustomers();
        seedSales();
    }
}
