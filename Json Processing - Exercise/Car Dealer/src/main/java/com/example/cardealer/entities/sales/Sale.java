package com.example.cardealer.entities.sales;

import com.example.cardealer.entities.cars.Car;
import com.example.cardealer.entities.customers.Customer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    private Car car;

    @ManyToOne(optional = false)
    private Customer customer;

    @Column(nullable = false)
    private int discount;

    public Sale() {
    }

    public Sale(Car car, Customer customer, int discount) {
        this.car = car;
        this.customer = customer;
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getDiscountPercentage() {
        return discount;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discount = discountPercentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return id.equals(sale.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
