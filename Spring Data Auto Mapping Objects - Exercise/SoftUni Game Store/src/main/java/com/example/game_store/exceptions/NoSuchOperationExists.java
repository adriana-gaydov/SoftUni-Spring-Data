package com.example.game_store.exceptions;

public class NoSuchOperationExists extends RuntimeException {

    public NoSuchOperationExists() {
        super("No Such Operation Exists!");
    }

    public NoSuchOperationExists(String message) {
        super(message);
    }
}
