package com.example.game_store.exceptions;

public class NoSuchAccountException extends RuntimeException {

    public NoSuchAccountException() {
        super("No such account exists!");
    }

    public NoSuchAccountException(String message) {
        super(message);
    }
}
