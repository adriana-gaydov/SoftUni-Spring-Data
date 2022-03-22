package com.example.products_shop.entities.users;

import com.example.products_shop.entities.products.SoldProductDTO;

import java.util.Set;

public class SellerFirstLastNameProductsSoldDTO {

    private String firstName;
    private String lastName;
    private Set<SoldProductDTO> soldProducts;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<SoldProductDTO> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<SoldProductDTO> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
