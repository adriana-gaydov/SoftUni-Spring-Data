package com.example.products_shop.services.impl;

import com.example.products_shop.entities.categories.CategoryByProductDTO;
import com.example.products_shop.entities.products.ProductNamePriceSellerDTO;
import com.example.products_shop.entities.users.SellerFirstLastNameProductsSoldDTO;
import com.example.products_shop.entities.users.UserProductsRootDTO;
import com.example.products_shop.services.CategoryService;
import com.example.products_shop.services.JSONExportService;
import com.example.products_shop.services.ProductService;
import com.example.products_shop.services.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class JSONExportServiceImpl implements JSONExportService {

    private ProductService productService;
    private UserService userService;
    private CategoryService categoryService;
    private Gson gson;

    @Autowired
    public JSONExportServiceImpl(ProductService productService, UserService userService, CategoryService categoryService) {
        this.productService = productService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void exportProductNamePriceSeller() throws IOException {
        List<ProductNamePriceSellerDTO> productNamePriceSellerDTOList =
                this.productService.getNamePriceSellerByPriceBetweenAndBuyerIsNullOrderByPriceAsc(500, 1000);

        export(gson.toJson(productNamePriceSellerDTOList));
    }

    @Override
    public void exportSellers() {
        List<SellerFirstLastNameProductsSoldDTO> sellers = this.userService.getSellersFirstLastNameProductsSold();

        export(gson.toJson(sellers));
    }

    @Override
    public void exportCategoryStats() {
        List<CategoryByProductDTO> categoryByProductStats = this.categoryService.getCategoryByProductStats();

        export(gson.toJson(categoryByProductStats));
    }

    @Override
    public void exportUsersAndProducts() {
        UserProductsRootDTO usersAndProducts = this.userService.getUsersAndProducts();

        export(gson.toJson(usersAndProducts));
    }

    private void export(String gson) {

        System.out.println(gson);
    }
}
