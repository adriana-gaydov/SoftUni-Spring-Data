package com.example.productshopxml.services.impl;

import com.example.productshopxml.entities.products.ProductNamePriceSellerNameDTO;
import com.example.productshopxml.entities.products.ProductNamePriceSellerNameRootDTO;
import com.example.productshopxml.repositories.ProductRepository;
import com.example.productshopxml.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductNamePriceSellerNameRootDTO getProductsInRange(float lower, float upper) {
        BigDecimal lowerBD = BigDecimal.valueOf(lower);
        BigDecimal upperBD = BigDecimal.valueOf(upper);

        List<ProductNamePriceSellerNameDTO> products = this.productRepository.findByPriceBetweenAndBuyerIsNullOrderByPriceAsc(lowerBD, upperBD).stream()
                .map(e -> new ProductNamePriceSellerNameDTO(e.getName(), e.getPrice(), e.getSeller()))
                .collect(Collectors.toList());

        return new ProductNamePriceSellerNameRootDTO(products);
    }
}
