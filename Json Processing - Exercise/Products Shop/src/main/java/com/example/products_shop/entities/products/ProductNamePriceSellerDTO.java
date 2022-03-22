package com.example.products_shop.entities.products;

import com.example.products_shop.entities.users.User;

import java.math.BigDecimal;

public class ProductNamePriceSellerDTO {

    private String name;
    private BigDecimal price;
    private String seller;

    public ProductNamePriceSellerDTO(String name, BigDecimal price, User seller) {
        this.name = name;
        this.price = price;

        if (seller.getFirstName() == null) {
            this.seller = seller.getFirstName();
        } else {
            this.seller = seller.getFirstName() + " " + seller.getLastName();
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

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
