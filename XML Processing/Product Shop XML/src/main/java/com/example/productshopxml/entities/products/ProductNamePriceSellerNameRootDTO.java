package com.example.productshopxml.entities.products;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductNamePriceSellerNameRootDTO {

    @XmlElement(name = "product")
    private List<ProductNamePriceSellerNameDTO> products;

    public ProductNamePriceSellerNameRootDTO() {
    }

    public ProductNamePriceSellerNameRootDTO(List<ProductNamePriceSellerNameDTO> products) {
        this.products = products;
    }

    public List<ProductNamePriceSellerNameDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductNamePriceSellerNameDTO> products) {
        this.products = products;
    }
}
