package com.example.game_store.exceptions;

public class WrongCredentialsException extends RuntimeException {

    public WrongCredentialsException() {
        super("Wrong credentials! Can't register!");
    }

    public WrongCredentialsException(String message) {
        super(message);
    }
}
