package com.example.advquerying;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.services.IngredientService;
import com.example.advquerying.services.ShampooService;
import com.example.advquerying.tools.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final ShampooService shampooService;
    private final IngredientService ingredientService;

    @Autowired
    public ConsoleRunner(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Pick task number:");
        int taskNumber = Reader.readInt();

        switch (taskNumber) {
            case 1 -> taskOne();
            case 2 -> taskTwo();
            case 3 -> taskThree();
            case 4 -> taskFour();
            case 5 -> taskFive();
            case 6 -> taskSix();
            case 7 -> taskSeven();
            case 8 -> taskEight();
            case 9 -> taskNine();
            case 10 -> taskTen();
            case 11 -> taskEleven();
        }
    }

    private void taskOne() throws IOException {
        Size wantedSize = Size.valueOf(Reader.readString());
        selectShampoosBySize(wantedSize);
    }

    private void taskTwo() throws IOException {
        Size wantedSize = Size.valueOf(Reader.readString());
        Long wantedLabelId = Reader.readLong();
        selectShampoosBySizeOrLabelId(wantedSize, wantedLabelId);
    }

    private void taskThree() throws IOException {
        double wantedPrice = Reader.readDouble();
        selectShampoosByPrice(wantedPrice);
    }

    private void taskFour() throws IOException {
        String wantedStart = Reader.readString();
        selectIngredientsByName(wantedStart);
    }

    private void taskFive() {
        Set<String> wantedIngredients = Set.of("Lavender", "Herbs", "Apple");
        selectIngredientsByNames(wantedIngredients);
    }

    private void taskSix() throws IOException {
        double wantedPrice = Reader.readDouble();
        countShampoosByPrice(wantedPrice);
    }

    private void taskSeven() throws IOException {
        Set<String> wantedIngredients = Set.of("Berry", "Mineral-Collagen");
        selectShampoosByIngredients(wantedIngredients);
    }

    private void taskEight() throws IOException {
        int wantedIngredientsCount = Reader.readInt();
        selectShampoosByIngredientsCount(wantedIngredientsCount);
    }

    private void taskNine() throws IOException {
        String wantedName = Reader.readString();
        deleteIngredientsByName(wantedName);
    }

    private void taskTen() throws IOException {
        updateIngredientsByPrice();
    }

    private void taskEleven() throws IOException {
        Set<String> ingredientNames = Set.of("Apple", "Cherry");
        updateIngredientsByNames(ingredientNames);
    }

    private void updateIngredientsByNames(Set<String> ingredientNames) {
        this.ingredientService.updateIngredientsByNames(ingredientNames);
    }

    private void updateIngredientsByPrice() {
        this.ingredientService.updateIngredientsByPrice();
    }

    private void deleteIngredientsByName(String wantedName) {
        this.ingredientService.deleteIngredientsByName(wantedName);
    }

    private void selectShampoosByIngredientsCount(int wantedIngredientsCount) {
        List<String> shampoos = this.shampooService.selectShampoosByIngredientsCount(wantedIngredientsCount);
        shampoos.forEach(System.out::println);
    }

    private void selectShampoosByIngredients(Set<String> wantedIngredients) {
        List<String> shampoos = this.shampooService.selectShampoosByIngredients(wantedIngredients);
        shampoos.forEach(System.out::println);
    }

    private void countShampoosByPrice(double wantedPrice) {
        System.out.println(this.shampooService.countShampoosByPrice(wantedPrice));
    }

    private void selectIngredientsByNames(Set<String> wantedIngredients) {
        List<Ingredient> ingredients = this.ingredientService.selectIngredientsByNames(wantedIngredients);
        printIngredientsName(ingredients);
    }

    private void selectIngredientsByName(String wantedStart) {
        List<Ingredient> ingredients = this.ingredientService.selectIngredientsByName(wantedStart);
        printIngredientsName(ingredients);
    }

    private void selectShampoosBySize(Size size) {
        List<Shampoo> shampoos = this.shampooService.selectShampoosBySize(size);
        printShampoos(shampoos);
    }

    private void selectShampoosByPrice(double price) {
        List<Shampoo> shampoos = this.shampooService.selectShampoosByPrice(price);
        printShampoos(shampoos);
    }

    private void selectShampoosBySizeOrLabelId(Size size, Long labelId) {
        List<Shampoo> shampoos = this.shampooService.selectShampoosBySizeOrLabelId(size, labelId);
        printShampoos(shampoos);
    }

    private void printShampoos(List<Shampoo> shampoos) {
        shampoos.forEach(s -> System.out.printf("%s %s %.2flv.%n",
                s.getBrand(), s.getSize(), s.getPrice()));
    }

    private void printIngredientsName(List<Ingredient> ingredients) {
        ingredients.forEach(i -> System.out.println(i.getName()));
    }

}
