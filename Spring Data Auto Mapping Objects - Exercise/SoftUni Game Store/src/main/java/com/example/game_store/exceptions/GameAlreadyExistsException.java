package com.example.game_store.exceptions;

public class GameAlreadyExistsException extends RuntimeException {
    public GameAlreadyExistsException() {
        super("Game already added!");
    }

    public GameAlreadyExistsException(String message) {
        super(message);
    }
}
