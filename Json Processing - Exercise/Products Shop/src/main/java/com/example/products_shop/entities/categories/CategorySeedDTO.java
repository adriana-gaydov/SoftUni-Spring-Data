package com.example.products_shop.entities.categories;

import com.example.products_shop.exceptions.NameNotInBoundsException;

public class CategorySeedDTO {

    private String name;

    public CategorySeedDTO(String name) {
        this.name = name;
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
}
