package com.example.cardealer.entities.parts;

import java.math.BigDecimal;

public class PartNamePriceDTO {

    private String Name;
    private BigDecimal Price;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public BigDecimal getPrice() {
        return Price;
    }

    public void setPrice(BigDecimal price) {
        Price = price;
    }
}
