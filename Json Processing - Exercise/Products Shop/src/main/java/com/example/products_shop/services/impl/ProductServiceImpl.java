package com.example.products_shop.services.impl;

import com.example.products_shop.entities.products.ProductNamePriceSellerDTO;
import com.example.products_shop.repositories.ProductRepository;
import com.example.products_shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductNamePriceSellerDTO> getNamePriceSellerByPriceBetweenAndBuyerIsNullOrderByPriceAsc(float lower, float upper) {
        BigDecimal lowerBD = BigDecimal.valueOf(lower);
        BigDecimal upperBD = BigDecimal.valueOf(upper);

        return this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(lowerBD, upperBD);
    }
}
