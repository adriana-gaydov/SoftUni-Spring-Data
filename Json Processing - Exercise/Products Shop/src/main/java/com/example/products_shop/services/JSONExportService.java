package com.example.products_shop.services;

import java.io.IOException;

public interface JSONExportService {

    void exportProductNamePriceSeller() throws IOException;
    void exportSellers();
    void exportCategoryStats();
    void exportUsersAndProducts();
}
