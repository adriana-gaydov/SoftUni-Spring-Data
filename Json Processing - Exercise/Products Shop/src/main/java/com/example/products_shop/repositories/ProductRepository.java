package com.example.products_shop.repositories;

import com.example.products_shop.entities.products.Product;
import com.example.products_shop.entities.products.ProductNamePriceSellerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<ProductNamePriceSellerDTO> findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(BigDecimal lower, BigDecimal upper);
}
