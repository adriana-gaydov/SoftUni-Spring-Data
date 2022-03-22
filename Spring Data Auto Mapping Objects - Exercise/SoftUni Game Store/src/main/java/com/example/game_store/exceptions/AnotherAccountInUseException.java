package com.example.game_store.exceptions;

public class AnotherAccountInUseException extends RuntimeException {

    public AnotherAccountInUseException() {
        super("Another user is logged in!");
    }

    public AnotherAccountInUseException(String message) {
        super(message);
    }
}
