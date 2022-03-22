package com.example.productshopxml.services.impl;

import com.example.productshopxml.entities.categories.CategoryProductCountPricesRootDTO;
import com.example.productshopxml.entities.products.ProductNamePriceSellerNameRootDTO;
import com.example.productshopxml.entities.users.UserCountSoldProductsRoot;
import com.example.productshopxml.services.CategoryService;
import com.example.productshopxml.entities.users.UserSoldProductsRootDTO;
import com.example.productshopxml.utils.XmlParser;
import com.example.productshopxml.services.ProductService;
import com.example.productshopxml.services.UserService;
import com.example.productshopxml.services.XMLExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;

@Service
public class XMLExportServiceImpl implements XMLExportService {

    private ProductService productService;
    private UserService userService;
    private CategoryService categoryService;

    @Autowired
    public XMLExportServiceImpl(ProductService productService, UserService userService, CategoryService categoryService) {
        this.productService = productService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void exportProductsInRange() throws JAXBException {
        ProductNamePriceSellerNameRootDTO productsInRange = this.productService.getProductsInRange(500, 1000);

        XmlParser.toFile(productsInRange);
    }

    @Override
    public void exportProductsSoldWithBuyer() throws JAXBException {
        UserSoldProductsRootDTO allUsersWithItemSold = this.userService.getAllUsersWithItemSold();

        XmlParser.toFile(allUsersWithItemSold);
    }

    @Override
    public void exportCategoriesWithCount() throws JAXBException {
        CategoryProductCountPricesRootDTO categoriesByProductsCount = this.categoryService.getCategoriesByProductsCount();

        XmlParser.toFile(categoriesByProductsCount);
    }

    @Override
    public void exportUsersWithProductsSoldWithCount() throws JAXBException {
        UserCountSoldProductsRoot users = this.userService.getAllUsersWithProductsSoldWithCount();

        XmlParser.toFile(users);
    }
}
