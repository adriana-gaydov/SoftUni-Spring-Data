package com.example.productshopxml.services;

import com.example.productshopxml.entities.categories.CategoryProductCountPricesRootDTO;

public interface CategoryService {

    CategoryProductCountPricesRootDTO getCategoriesByProductsCount();
}
