package com.example.cardealer.services.impl;

import com.example.cardealer.entities.customers.Customer;
import com.example.cardealer.entities.customers.CustomerSalesDTO;
import com.example.cardealer.entities.customers.CustomerSalesDiscountDTO;
import com.example.cardealer.repositories.CustomerRepository;
import com.example.cardealer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomersOrderByBirthDateAscThenByYoungDriversIsFalse() {
        return this.customerRepository.findAllOrderByBirthDateAscThenByYoungDriversIsFalse();
    }

    @Override
    public List<CustomerSalesDTO> getTotalSalesByCustomer() {
        return this.customerRepository.findAllSales();
    }

    @Override
    public List<CustomerSalesDiscountDTO> getTotalSalesAndDiscount() {
        return this.customerRepository.findAllSalesWithDiscount();
    }
}
