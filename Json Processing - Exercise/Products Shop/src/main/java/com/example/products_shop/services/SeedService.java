package com.example.products_shop.services;

import java.io.FileNotFoundException;

public interface SeedService {
    String CATEGORIES_PATH = "src/main/resources/files/categories.json";
    String PRODUCTS_PATH = "src/main/resources/files/products.json";
    String USERS_PATH = "src/main/resources/files/users.json";


    void seedCategories() throws FileNotFoundException;

    void seedProducts() throws FileNotFoundException;

    void seedUsers() throws FileNotFoundException;

    default void seedDatabase() throws FileNotFoundException {
        seedUsers();
        seedCategories();
        seedProducts();
    }
}
