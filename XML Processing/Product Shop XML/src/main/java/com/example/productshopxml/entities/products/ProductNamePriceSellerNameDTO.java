package com.example.productshopxml.entities.products;

import com.example.productshopxml.entities.users.User;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductNamePriceSellerNameDTO {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private BigDecimal price;

    @XmlAttribute
    private String seller;

    public ProductNamePriceSellerNameDTO() {
    }

    public ProductNamePriceSellerNameDTO(String name, BigDecimal price, User seller) {
        this.name = name;
        this.price = price;
        this.seller = seller.getFullName();
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
