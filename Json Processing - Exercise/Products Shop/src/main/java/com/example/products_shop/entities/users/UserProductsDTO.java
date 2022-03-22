package com.example.products_shop.entities.users;

import com.example.products_shop.entities.products.SoldProduct2DTO;
import com.example.products_shop.entities.products.SoldProduct2RootDTO;

import java.util.Set;

public class UserProductsDTO {

    private String firstName;
    private String lastName;
    private Integer age;
    private SoldProduct2RootDTO soldProducts;

    public UserProductsDTO() {
    }

    public UserProductsDTO(String firstName, String lastName, Integer age, SoldProduct2RootDTO soldProducts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.soldProducts = soldProducts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
