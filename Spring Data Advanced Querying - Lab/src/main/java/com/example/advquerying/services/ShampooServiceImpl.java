package com.example.advquerying.services;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
public class ShampooServiceImpl implements ShampooService {

    private final ShampooRepository shampooRepository;

    @Autowired
    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<Shampoo> selectShampoosBySize(Size size) {
        return this.shampooRepository.findAllBySizeOrderById(size);
    }

    @Override
    public List<Shampoo> selectShampoosByPrice(double price) {
        BigDecimal actPrice = BigDecimal.valueOf(price);
        return this.shampooRepository.findAllByPriceGreaterThanOrderByPriceDesc(actPrice);
    }

    @Override
    public List<Shampoo> selectShampoosBySizeOrLabelId(Size size, Long labelId) {
        return this.shampooRepository.findAllBySizeOrLabelIdOrderByPrice(size, labelId);
    }

    @Override
    public int countShampoosByPrice(double wantedPrice) {
        BigDecimal actPrice = BigDecimal.valueOf(wantedPrice);
        return this.shampooRepository.countByPriceLessThan(actPrice);
    }

    @Override
    public List<String> selectShampoosByIngredients(Set<String> wantedIngredients) {
        return this.shampooRepository.findAllByIngredientNameIn(wantedIngredients);
    }

    @Override
    public List<String> selectShampoosByIngredientsCount(int wantedIngredientsCount) {
        return this.shampooRepository.findAllByIngredientsCountLessThan(wantedIngredientsCount);
    }
}
