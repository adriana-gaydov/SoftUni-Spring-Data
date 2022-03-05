package com.example.advquerying.repositories;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {
    List<Shampoo> findAllBySizeOrderById(Size size);
    List<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal actPrice);
    List<Shampoo> findAllBySizeOrLabelIdOrderByPrice(Size size, Long labelId);
    int countByPriceLessThan(BigDecimal wantedPrice);

    @Query(value = "SELECT DISTINCT s.brand FROM Shampoo s" +
            " JOIN s.ingredients i" +
            " WHERE i.name IN :ingredientNames")
    List<String> findAllByIngredientNameIn
            (@Param(value = "ingredientNames") Set<String> wantedIngredients);

    @Query(value = "SELECT DISTINCT s.brand FROM Shampoo s" +
            " WHERE s.ingredients.size < :ingredientsCount")
    List<String> findAllByIngredientsCountLessThan(@Param("ingredientsCount") int wantedIngredientsCount);
}
