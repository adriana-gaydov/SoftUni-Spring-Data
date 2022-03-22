package com.example.products_shop.services;

import com.example.products_shop.entities.categories.CategoryByProductDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryByProductDTO> getCategoryByProductStats();
}
