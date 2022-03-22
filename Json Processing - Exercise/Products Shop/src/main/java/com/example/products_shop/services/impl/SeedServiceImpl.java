package com.example.products_shop.services.impl;

import com.example.products_shop.entities.categories.Category;
import com.example.products_shop.entities.products.Product;
import com.example.products_shop.entities.users.User;
import com.example.products_shop.entities.categories.CategorySeedDTO;
import com.example.products_shop.entities.products.ProductSeedDTO;
import com.example.products_shop.entities.users.UserSeedDTO;
import com.example.products_shop.repositories.CategoryRepository;
import com.example.products_shop.repositories.ProductRepository;
import com.example.products_shop.repositories.UserRepository;
import com.example.products_shop.services.SeedService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class SeedServiceImpl implements SeedService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final Gson gson;
    private final ModelMapper mapper;

    @Autowired
    public SeedServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;

        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        this.mapper = new ModelMapper();
    }

    @Override
    public void seedCategories() throws FileNotFoundException {
        Arrays.stream(this.gson.fromJson(new FileReader(CATEGORIES_PATH), CategorySeedDTO[].class))
                .map(e -> mapper.map(e, Category.class))
                .forEach(this.categoryRepository::save);
    }

    @Override
    public void seedProducts() throws FileNotFoundException {
        Arrays.stream(this.gson.fromJson(new FileReader(PRODUCTS_PATH), ProductSeedDTO[].class))
                .map(e -> mapper.map(e, Product.class))
                .forEach(p -> {
                    setRandomBuyer(p);
                    setRandomSeller(p);
                    setRandomCategories(p);
                    this.productRepository.save(p);
                });

    }

    private void setRandomCategories(Product p) {
        long categoriesCount = this.categoryRepository.count();

        Set<Category> categoriesToAdd = new HashSet<>();

        for (int i = 1; i <= categoriesCount; i++) {

            long randomCategoryId = new Random().nextLong(categoriesCount) + 1;

            categoriesToAdd.add(this.categoryRepository.getById(randomCategoryId));
        }

        p.setCategories(categoriesToAdd);
    }

    private void setRandomSeller(Product p) {
        long randomUserId = getRandomUserId();

        User user = this.userRepository.getById(randomUserId);

        p.setSeller(user);
    }

    private void setRandomBuyer(Product p) {
        long randomUserId = getRandomUserId();

        if (randomUserId % 6 == 0) {
            return;
        }

        User user = this.userRepository.getById(randomUserId);

        p.setBuyer(user);
    }

    private long getRandomUserId() {
        long countUsers = this.userRepository.count();

        return new Random().nextLong(countUsers) + 1;
    }

    @Override
    public void seedUsers() throws FileNotFoundException {
        Arrays.stream(this.gson.fromJson(new FileReader(USERS_PATH), UserSeedDTO[].class))
                .map(e -> mapper.map(e, User.class))
                .forEach(this.userRepository::save);
    }
}
