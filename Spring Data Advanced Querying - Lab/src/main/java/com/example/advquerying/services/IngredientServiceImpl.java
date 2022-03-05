package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> selectIngredientsByName(String start) {
        return this.ingredientRepository.findAllByNameStartingWith(start);
    }

    @Override
    public List<Ingredient> selectIngredientsByNames(Set<String> wantedIngredients) {
        return this.ingredientRepository.findAllByNameInOrderByPriceAsc(wantedIngredients);
    }

    @Override
    public void deleteIngredientsByName(String wantedName) {
        this.ingredientRepository.deleteByName(wantedName);
    }

    @Override
    public void updateIngredientsByPrice() {
        this.ingredientRepository.updateIngredientsPrice();
    }

    @Override
    public void updateIngredientsByNames(Set<String> ingredientNames) {
        this.ingredientRepository.updateIngredientsByNames(ingredientNames);
    }
}
