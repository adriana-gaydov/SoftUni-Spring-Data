package com.example.game_store.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
        super("Can't register with the same email!");
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
