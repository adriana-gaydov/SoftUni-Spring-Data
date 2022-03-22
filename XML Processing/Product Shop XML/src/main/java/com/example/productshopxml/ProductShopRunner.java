package com.example.productshopxml;

import com.example.productshopxml.services.SeedService;
import com.example.productshopxml.services.XMLExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.naming.OperationNotSupportedException;
import java.util.Scanner;

@Component
public class ProductShopRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final XMLExportService xmlExportService;

    @Autowired
    public ProductShopRunner(SeedService seedService, XMLExportService xmlExportService) {
        this.seedService = seedService;
        this.xmlExportService = xmlExportService;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Input QUERY no:");
        int taskNo = Integer.parseInt(new Scanner(System.in).nextLine());

        switch (taskNo) {
            case 1 -> xmlExportService.exportProductsInRange();
            case 2 -> xmlExportService.exportProductsSoldWithBuyer();
            case 3 -> xmlExportService.exportCategoriesWithCount();
            case 4 -> xmlExportService.exportUsersWithProductsSoldWithCount();

            default -> throw new OperationNotSupportedException();
        }
    }
}
