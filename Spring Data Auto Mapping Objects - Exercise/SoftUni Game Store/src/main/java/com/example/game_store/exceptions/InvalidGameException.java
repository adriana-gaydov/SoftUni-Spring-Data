package com.example.game_store.exceptions;

public class InvalidGameException extends RuntimeException {

    public InvalidGameException() {
        super("Game doesn't match the criteria!");
    }

    public InvalidGameException(String message) {
        super(message);
    }
}
