package com.example.game_store.entities.games;

import com.example.game_store.exceptions.InvalidGameException;
import com.example.game_store.utils.CredentialValidation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddGameDTO {

    private String title;
    private BigDecimal price;
    private float size;
    private String trailer;
    private String thumbnailURL;
    private String description;
    private LocalDate releaseDate;

    public AddGameDTO(String[] commandParts) {
        title = commandParts[1];
        price = new BigDecimal(commandParts[2]);
        size = Float.parseFloat(commandParts[3]);
        trailer = commandParts[4];
        thumbnailURL = commandParts[5];
        description = commandParts[6];
        releaseDate = LocalDate.parse(commandParts[7], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        validate();

        trailer = trailer.substring(trailer.length() - 11);
    }

    private void validate() {
        if (!CredentialValidation.isValidGame(title, price, size, trailer, thumbnailURL, description)) {
            throw new InvalidGameException();
        }
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public float getSize() {
        return size;
    }

    public String getTrailer() {
        return trailer;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
