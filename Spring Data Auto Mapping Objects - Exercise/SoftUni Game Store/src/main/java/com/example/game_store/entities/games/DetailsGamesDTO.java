package com.example.game_store.entities.games;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class DetailsGamesDTO {
    private final String title;
    private final BigDecimal price;
    private final String description;
    private final LocalDate releaseDate;

    public DetailsGamesDTO(String title, BigDecimal price, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    @Override
    public String toString() {
        return String.format("Title: %s%n" +
                "Price: %.2f%n" +
                "Description: %s%n" +
                "Release date: %s", title, price, description, releaseDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailsGamesDTO that = (DetailsGamesDTO) o;
        return Objects.equals(title, that.title) && Objects.equals(price, that.price) && Objects.equals(description, that.description) && Objects.equals(releaseDate, that.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price, description, releaseDate);
    }
}
