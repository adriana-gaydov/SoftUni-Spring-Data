package com.example.products_shop.services.impl;

import com.example.products_shop.entities.categories.CategoryByProductDTO;
import com.example.products_shop.repositories.CategoryRepository;
import com.example.products_shop.services.CategoryService;
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
    public List<CategoryByProductDTO> getCategoryByProductStats() {
        return this.categoryRepository.getCategoryByProductStats();
    }
}
