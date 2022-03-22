package com.example.productshopxml.services;

import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface SeedService {
    String CATEGORIES_PATH = "src/main/resources/files/categories.xml";
    String USERS_PATH = "src/main/resources/files/users.xml";
    String PRODUCTS_PATH = "src/main/resources/files/products.xml";

    void seedCategories() throws JAXBException, FileNotFoundException;
    void seedUsers() throws JAXBException, FileNotFoundException;
    void seedProducts() throws JAXBException, FileNotFoundException;

    @Transactional
    default void seedDatabase() throws JAXBException, FileNotFoundException {
        seedCategories();
        seedUsers();
        seedProducts();
    }
}
