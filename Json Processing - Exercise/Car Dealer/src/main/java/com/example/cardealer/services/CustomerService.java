package com.example.cardealer.services;

import com.example.cardealer.entities.customers.Customer;
import com.example.cardealer.entities.customers.CustomerSalesDTO;
import com.example.cardealer.entities.customers.CustomerSalesDiscountDTO;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomersOrderByBirthDateAscThenByYoungDriversIsFalse();
    List<CustomerSalesDTO> getTotalSalesByCustomer();
    List<CustomerSalesDiscountDTO> getTotalSalesAndDiscount();
}
