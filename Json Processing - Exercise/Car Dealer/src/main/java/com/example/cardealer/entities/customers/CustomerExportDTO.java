package com.example.cardealer.entities.customers;

import com.example.cardealer.entities.sales.Sale;
import com.example.cardealer.entities.sales.SaleExportDTO;

import java.util.HashSet;
import java.util.Set;

public class CustomerExportDTO {

    private Long Id;
    private String Name;
    private String BirthDate;
    private boolean IsYoungDriver;
    //private Set<Sale> Sales;
    private Set<SaleExportDTO> Sales;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return IsYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        IsYoungDriver = youngDriver;
    }

    public Set<SaleExportDTO> getSales() {
        return Sales;
    }

    public void setSales(Set<SaleExportDTO> sales) {
        Sales = sales;
    }
}
