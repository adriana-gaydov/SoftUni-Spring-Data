package com.example.productshopxml.services.impl;

import com.example.productshopxml.entities.categories.Category;
import com.example.productshopxml.entities.categories.ImportCategoryRootDTO;
import com.example.productshopxml.entities.products.ImportProductRootDTO;
import com.example.productshopxml.entities.products.Product;
import com.example.productshopxml.repositories.CategoryRepository;
import com.example.productshopxml.repositories.ProductRepository;
import com.example.productshopxml.repositories.UserRepository;
import com.example.productshopxml.entities.users.ImportUserRootDTO;
import com.example.productshopxml.entities.users.User;
import com.example.productshopxml.utils.ValidationUtil;
import com.example.productshopxml.utils.XmlParser;
import com.example.productshopxml.services.SeedService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {

    private CategoryRepository categoryRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private ModelMapper mapper;
    private ValidationUtil validationUtil;

    @Autowired
    public SeedServiceImpl(CategoryRepository categoryRepository, UserRepository userRepository, ProductRepository productRepository, ModelMapper mapper, ValidationUtil validationUtil) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedCategories() throws JAXBException, FileNotFoundException {
        ImportCategoryRootDTO importCategoryRootDTO = XmlParser.fromFile(CATEGORIES_PATH, ImportCategoryRootDTO.class);

        ArrayList<Category> categories = importCategoryRootDTO.getCategories().stream()
                .filter(e -> validationUtil.isValid(e))
                .map(e -> mapper.map(e, Category.class))
                .collect(Collectors.toCollection(ArrayList::new));

        this.categoryRepository.saveAll(categories);
    }

    @Override
    public void seedUsers() throws JAXBException, FileNotFoundException {
        ImportUserRootDTO importUserRootDTO = XmlParser.fromFile(USERS_PATH, ImportUserRootDTO.class);

        ArrayList<User> users = importUserRootDTO.getUsers().stream()
                .filter(e -> validationUtil.isValid(e))
                .map(e -> mapper.map(e, User.class))
                .collect(Collectors.toCollection(ArrayList::new));

        this.userRepository.saveAll(users);
    }

    @Override
    @Transactional
    public void seedProducts() throws JAXBException, FileNotFoundException {
        ImportProductRootDTO importProductRootDTO = XmlParser.fromFile(PRODUCTS_PATH, ImportProductRootDTO.class);

        ArrayList<Product> products = importProductRootDTO.getProducts().stream()
                .filter(e -> validationUtil.isValid(e))
                .map(e -> mapper.map(e, Product.class))
                .collect(Collectors.toCollection(ArrayList::new));

        products.forEach(e -> {
            setRandomBuyer(e);
            setRandomCategories(e);
            setRandomSeller(e);
        });

        this.productRepository.saveAll(products);
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
}
