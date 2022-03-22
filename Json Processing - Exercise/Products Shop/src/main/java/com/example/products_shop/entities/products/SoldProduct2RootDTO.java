package com.example.products_shop.entities.products;

import java.util.Set;

public class SoldProduct2RootDTO {

    private long count;
    private Set<SoldProduct2DTO> products;

    public SoldProduct2RootDTO(Set<SoldProduct2DTO> products) {
        this.products = products;
        this.count = products.size();
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Set<SoldProduct2DTO> getProducts() {
        return products;
    }

    public void setProducts(Set<SoldProduct2DTO> products) {
        this.products = products;
    }
}
