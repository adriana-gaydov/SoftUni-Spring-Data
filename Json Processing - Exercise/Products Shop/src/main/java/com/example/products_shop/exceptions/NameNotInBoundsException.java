package com.example.products_shop.exceptions;

public class NameNotInBoundsException extends RuntimeException {

    public NameNotInBoundsException() {
        super("Name isn't in correct length!");
    }

    public NameNotInBoundsException(String message) {
        super(message);
    }
}
