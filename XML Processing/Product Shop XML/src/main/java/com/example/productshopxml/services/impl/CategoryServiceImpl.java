package com.example.productshopxml.services.impl;

import com.example.productshopxml.entities.categories.CategoryProductCountPricesDTO;
import com.example.productshopxml.entities.categories.CategoryProductCountPricesRootDTO;
import com.example.productshopxml.repositories.CategoryRepository;
import com.example.productshopxml.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryProductCountPricesRootDTO getCategoriesByProductsCount() {
        List<CategoryProductCountPricesDTO> categories = this.categoryRepository.findByProductsCount();

        return new CategoryProductCountPricesRootDTO(categories);
    }
}
