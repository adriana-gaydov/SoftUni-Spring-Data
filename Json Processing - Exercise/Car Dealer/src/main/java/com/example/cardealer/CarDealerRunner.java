package com.example.cardealer;

import com.example.cardealer.services.JSONConverterService;
import com.example.cardealer.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CarDealerRunner implements CommandLineRunner {

    private SeedService seedService;
    private JSONConverterService jsonConverterService;

    @Autowired
    public CarDealerRunner(SeedService seedService, JSONConverterService jsonConverterService) {
        this.seedService = seedService;
        this.jsonConverterService = jsonConverterService;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        //seedService.seedDatabase();
        this.jsonConverterService.exportCustomerTotalSalesAndDiscount();
    }
}
