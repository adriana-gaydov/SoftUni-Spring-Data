package com.example.productshopxml.entities.users;

import com.example.productshopxml.entities.products.ProductNamePriceBuyerNamesRootDTO;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSoldProductsDTO {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlElement(name = "sold-products")
    private ProductNamePriceBuyerNamesRootDTO productsSold;

    public UserSoldProductsDTO() {
    }

    public UserSoldProductsDTO(String firstName, String lastName, ProductNamePriceBuyerNamesRootDTO productsSold) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.productsSold = productsSold;
    }

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

    public ProductNamePriceBuyerNamesRootDTO getProductsSold() {
        return productsSold;
    }

    public void setProductsSold(ProductNamePriceBuyerNamesRootDTO productsSold) {
        this.productsSold = productsSold;
    }
}

