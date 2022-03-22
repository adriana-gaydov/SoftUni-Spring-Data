package com.example.productshopxml.entities.products;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductNamePriceBuyerNamesRootDTO {

    @XmlElement(name = "product")
   private Set<ProductNamePriceBuyerNamesDTO> productsSold;

    public ProductNamePriceBuyerNamesRootDTO(Set<ProductNamePriceBuyerNamesDTO> productsSold) {
        this.productsSold = productsSold;
    }

    public ProductNamePriceBuyerNamesRootDTO() {
    }

    public Set<ProductNamePriceBuyerNamesDTO> getProductsSold() {
        return productsSold;
    }

    public void setProductsSold(Set<ProductNamePriceBuyerNamesDTO> productsSold) {
        this.productsSold = productsSold;
    }
}
