package com.example.products_shop.entities.products;

import com.example.products_shop.exceptions.NameNotInBoundsException;

import java.math.BigDecimal;

public class ProductSeedDTO {

    private String name;

    private BigDecimal price;

    public ProductSeedDTO(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
        validate();
    }

    private void validate() {
        if (this.name.length() < 3 || this.name.length() > 15) {
            throw new NameNotInBoundsException();
        }
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
