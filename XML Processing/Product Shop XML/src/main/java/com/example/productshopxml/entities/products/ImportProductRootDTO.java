package com.example.productshopxml.entities.products;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportProductRootDTO {

    @XmlElement(name = "product")
    private Set<ImportProductDTO> products;

    public Set<ImportProductDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<ImportProductDTO> products) {
        this.products = products;
    }
}
