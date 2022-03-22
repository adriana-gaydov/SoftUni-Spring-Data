package com.example.productshopxml.entities.categories;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryProductCountPricesRootDTO {

    @XmlElement(name = "category")
    private List<CategoryProductCountPricesDTO> categories;

    public CategoryProductCountPricesRootDTO() {
    }

    public CategoryProductCountPricesRootDTO(List<CategoryProductCountPricesDTO> categories) {
        this.categories = categories;
    }

    public List<CategoryProductCountPricesDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryProductCountPricesDTO> categories) {
        this.categories = categories;
    }
}
