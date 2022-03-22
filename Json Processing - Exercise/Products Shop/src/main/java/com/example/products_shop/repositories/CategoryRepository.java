package com.example.products_shop.repositories;

import com.example.products_shop.entities.categories.Category;
import com.example.products_shop.entities.categories.CategoryByProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


    @Query("SELECT new com.example.products_shop.entities.categories.CategoryByProductDTO " +
            "(c.name, COUNT(p), AVG(p.price), SUM(p.price)) FROM Product p JOIN p.categories c GROUP BY c")
    List<CategoryByProductDTO> getCategoryByProductStats();

}
