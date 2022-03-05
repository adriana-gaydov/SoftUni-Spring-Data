package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findAllByNameStartingWith(String start);
    List<Ingredient> findAllByNameInOrderByPriceAsc(Set<String> wantedIngredients);

    @Modifying
    void deleteByName(String wantedName);

    @Modifying
    @Query("UPDATE Ingredient i" +
            " SET i.price = i.price * 1.1")
    void updateIngredientsPrice();

    @Modifying
    @Query("UPDATE Ingredient i" +
            " SET i.price = i.price * 1.1" +
            " WHERE i.name IN :ingredientNames")
    void updateIngredientsByNames(Set<String> ingredientNames);
}
