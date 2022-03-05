package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;

import java.util.List;
import java.util.Set;

public interface IngredientService {
    List<Ingredient> selectIngredientsByName(String start);
    List<Ingredient> selectIngredientsByNames(Set<String> wantedIngredients);

    void deleteIngredientsByName(String wantedName);

    void updateIngredientsByPrice();

    void updateIngredientsByNames(Set<String> ingredientNames);
}
