package com.example.cardealer.entities.customers;

import com.example.cardealer.entities.cars.Car;
import com.example.cardealer.entities.cars.CarExportDTO;

import java.math.BigDecimal;

public class CustomerSalesDiscountDTO {

    private CarExportDTO car;
    private String customerName;
    private double discount;
    private BigDecimal price;
    private BigDecimal priceWithDiscount;

    public CustomerSalesDiscountDTO(Car car, String customerName, int discount, BigDecimal price, BigDecimal priceWithDiscount) {
        this.car = new CarExportDTO(car.getMake(), car.getModel(), car.getTravelledDistance());
        this.customerName = customerName;
        this.discount = discount / 100.00;
        this.price = price;
        this.priceWithDiscount = priceWithDiscount;
    }

    public CarExportDTO getCar() {
        return car;
    }

    public void setCar(CarExportDTO car) {
        this.car = car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
