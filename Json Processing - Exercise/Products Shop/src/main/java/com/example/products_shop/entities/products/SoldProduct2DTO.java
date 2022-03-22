package com.example.products_shop.entities.products;

import java.math.BigDecimal;
import java.util.Set;

public class SoldProduct2DTO {

    private String name;
    private BigDecimal price;

    public SoldProduct2DTO() {
    }

    public SoldProduct2DTO(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
