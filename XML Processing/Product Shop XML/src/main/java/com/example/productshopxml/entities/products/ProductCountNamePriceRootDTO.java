package com.example.productshopxml.entities.products;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductCountNamePriceRootDTO {

    @XmlAttribute(name = "count")
    private long productsCount;

    @XmlElement(name = "product")
    private List<ProductCountNamePriceDTO> products;

    public ProductCountNamePriceRootDTO() {}

    public ProductCountNamePriceRootDTO(List<ProductCountNamePriceDTO> products) {
        this.products = products;
        this.productsCount = products.size();
    }

    public long getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(long productsCount) {
        this.productsCount = productsCount;
    }

    public List<ProductCountNamePriceDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductCountNamePriceDTO> products) {
        this.products = products;
    }
}
