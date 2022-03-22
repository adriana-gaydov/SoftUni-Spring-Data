package com.example.productshopxml.entities.users;

import com.example.productshopxml.entities.products.ProductCountNamePriceRootDTO;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserCountSoldProductsDTO {


    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlAttribute
    private Integer age;

    @XmlElement(name = "sold-products")
    private ProductCountNamePriceRootDTO soldProducts;

    public UserCountSoldProductsDTO() {
    }

    public UserCountSoldProductsDTO(String firstName, String lastName, Integer age, ProductCountNamePriceRootDTO soldProducts) {
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ProductCountNamePriceRootDTO getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(ProductCountNamePriceRootDTO soldProducts) {
        this.soldProducts = soldProducts;
    }
}
