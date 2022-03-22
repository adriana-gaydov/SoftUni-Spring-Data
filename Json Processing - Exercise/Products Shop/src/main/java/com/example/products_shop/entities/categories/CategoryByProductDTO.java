package com.example.products_shop.entities.categories;

import java.math.BigDecimal;

public class CategoryByProductDTO {

    private String category;
    private Long productsCount;
    private double averagePrice;
    private BigDecimal totalRevenue;

    public CategoryByProductDTO(String category, Long productsCount, double averagePrice, BigDecimal totalRevenue) {
        this.category = category;
        this.productsCount = productsCount;
        this.averagePrice = averagePrice;
        this.totalRevenue = totalRevenue;
    }


}
