package com.example.productshopxml.entities.categories;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportCategoryRootDTO {

    @XmlElement(name = "category")
    private Set<ImportCategoryDTO> categories;

    public Set<ImportCategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(Set<ImportCategoryDTO> categories) {
        this.categories = categories;
    }
}
