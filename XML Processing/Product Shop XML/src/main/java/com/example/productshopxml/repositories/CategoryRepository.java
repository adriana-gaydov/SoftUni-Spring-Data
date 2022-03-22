package com.example.productshopxml.repositories;

import com.example.productshopxml.entities.categories.Category;
import com.example.productshopxml.entities.categories.CategoryProductCountPricesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT new com.example.productshopxml.entities.categories.CategoryProductCountPricesDTO" +
            "(c.name, COUNT(p), AVG(p.price), SUM(p.price)) FROM Product p JOIN p.categories c GROUP BY c")
    List<CategoryProductCountPricesDTO> findByProductsCount();
}
