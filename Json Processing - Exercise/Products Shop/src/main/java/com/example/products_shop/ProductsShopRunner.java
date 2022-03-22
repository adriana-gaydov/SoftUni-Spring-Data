package com.example.products_shop;

import com.example.products_shop.services.JSONExportService;
import com.example.products_shop.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.OperationNotSupportedException;
import java.util.Scanner;
import java.util.regex.Pattern;

@Component
public class ProductsShopRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final JSONExportService jsonExportService;

    @Autowired
    public ProductsShopRunner(SeedService seedService, JSONExportService jsonExportService) {
        this.seedService = seedService;
        this.jsonExportService = jsonExportService;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        System.out.println("Input query number:");
        int queryNo = Integer.parseInt(new Scanner(System.in).nextLine());

        switch (queryNo) {
            case 1 -> this.jsonExportService.exportProductNamePriceSeller();
            case 2 -> this.jsonExportService.exportSellers();
            case 3 -> this.jsonExportService.exportCategoryStats();
            case 4 -> this.jsonExportService.exportUsersAndProducts();
            default -> throw new OperationNotSupportedException();
        }
    }


}
