package com.example.advquerying.services;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;

import java.util.List;
import java.util.Set;

public interface ShampooService {
    List<Shampoo> selectShampoosBySize(Size size);
    List<Shampoo> selectShampoosByPrice(double price);
    List<Shampoo> selectShampoosBySizeOrLabelId(Size size, Long labelId);
    int countShampoosByPrice(double wantedPrice);
    List<String> selectShampoosByIngredients(Set<String> wantedIngredients);
    List<String> selectShampoosByIngredientsCount(int wantedIngredientsCount);
}
