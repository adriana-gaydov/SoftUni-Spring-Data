package com.example.productshopxml.services;

import com.example.productshopxml.entities.products.ProductNamePriceSellerNameRootDTO;

public interface ProductService {

    ProductNamePriceSellerNameRootDTO getProductsInRange(float lower, float upper);
}
