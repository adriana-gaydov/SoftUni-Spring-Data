package com.example.game_store.exceptions;

public class InsufficientPermissionException extends RuntimeException {

    public InsufficientPermissionException() {
        super("You don't have permission to perform this action!");
    }

    public InsufficientPermissionException(String message) {
        super(message);
    }
}
