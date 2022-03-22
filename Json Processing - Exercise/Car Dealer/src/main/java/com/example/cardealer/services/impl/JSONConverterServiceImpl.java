package com.example.cardealer.services.impl;

import com.example.cardealer.entities.cars.CarIdMakeModelTrDistDTO;
import com.example.cardealer.entities.customers.CustomerExportDTO;
import com.example.cardealer.entities.suppliers.SupplierIdNamePCountDTO;
import com.example.cardealer.services.CarService;
import com.example.cardealer.services.CustomerService;
import com.example.cardealer.services.JSONConverterService;
import com.example.cardealer.services.SupplierService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JSONConverterServiceImpl implements JSONConverterService {

    private CustomerService customerService;
    private CarService carService;
    private SupplierService supplierService;
    private Gson gson;
    private ModelMapper mapper;

    @Autowired
    public JSONConverterServiceImpl(CustomerService customerService, CarService carService, SupplierService supplierService, Gson gson, ModelMapper mapper) {
        this.customerService = customerService;
        this.carService = carService;
        this.supplierService = supplierService;
        this.gson = gson;
        this.mapper = mapper;
    }


    @Override
    public void exportOrderedCustomers() {
        ArrayList<CustomerExportDTO> customerExportDTOS = this.customerService.getAllCustomersOrderByBirthDateAscThenByYoungDriversIsFalse()
                .stream().map(e -> mapper.map(e, CustomerExportDTO.class))
                .collect(Collectors.toCollection(ArrayList::new));

        String s = this.gson.toJson(customerExportDTOS);
        System.out.println(s);
    }

    @Override
    public void exportCarsFromMakeToyota() {
        HashSet<CarIdMakeModelTrDistDTO> cars = this.carService.getCarsFromMake("Toyota").stream()
                .map(e -> mapper.map(e, CarIdMakeModelTrDistDTO.class))
                .collect(Collectors.toCollection(HashSet::new));

        String s = this.gson.toJson(cars);
        System.out.println(s);
    }

    @Override
    public void exportLocalSuppliers() {
        Set<SupplierIdNamePCountDTO> suppliers = new HashSet<>(this.supplierService.getLocalSuppliers());

        String s = this.gson.toJson(suppliers);
        System.out.println(s);
    }

    @Override
    public void exportCarsWithTheirListOfParts() {
        String s = this.gson.toJson(this.carService.getCarsWithTheirListOfParts());
        System.out.println(s);
    }

    @Override
    public void exportCustomerTotalSales() {
        String s = this.gson.toJson(this.customerService.getTotalSalesByCustomer());
        System.out.println(s);
    }

    @Override
    public void exportCustomerTotalSalesAndDiscount() {
        String s = this.gson.toJson(this.customerService.getTotalSalesAndDiscount());
        System.out.println(s);
    }
}
