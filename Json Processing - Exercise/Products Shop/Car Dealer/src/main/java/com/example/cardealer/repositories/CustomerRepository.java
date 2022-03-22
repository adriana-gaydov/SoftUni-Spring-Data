package com.example.cardealer.repositories;

import com.example.cardealer.entities.customers.CustomerSalesDTO;
import com.example.cardealer.entities.customers.Customer;
import com.example.cardealer.entities.customers.CustomerSalesDiscountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c ORDER BY c.birthDate, c.isYoungDriver DESC")
    List<Customer> findAllOrderByBirthDateAscThenByYoungDriversIsFalse();

    @Query("SELECT new com.example.cardealer.entities.customers.CustomerSalesDTO(" +
            "cust.name, COUNT(c), SUM(p.price)*(1-s.discount/100)) FROM Sale s JOIN s.car c JOIN s.car.parts p JOIN s.customer cust GROUP BY cust")
    List<CustomerSalesDTO> findAllSales();

    @Query("SELECT new com.example.cardealer.entities.customers.CustomerSalesDiscountDTO(" +
            "c, cust.name, s.discount, SUM(p.price), SUM(p.price)*(1-s.discount/100)) FROM Sale s JOIN s.car c JOIN s.car.parts p JOIN s.customer cust group by s")
    List<CustomerSalesDiscountDTO> findAllSalesWithDiscount();
}
