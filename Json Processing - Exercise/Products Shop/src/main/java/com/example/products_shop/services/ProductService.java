package com.example.products_shop.services;

import com.example.products_shop.entities.products.ProductNamePriceSellerDTO;

import java.util.List;

public interface ProductService {

    List<ProductNamePriceSellerDTO> getNamePriceSellerByPriceBetweenAndBuyerIsNullOrderByPriceAsc(float lower, float upper);
}
