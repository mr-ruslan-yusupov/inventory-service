package com.inventory.service.exceptions;

public class CategoryNotEmptyException extends RuntimeException {
    public CategoryNotEmptyException() {
    }

    public CategoryNotEmptyException(String message) {
        super(message);
    }

    public CategoryNotEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryNotEmptyException(Throwable cause) {
        super(cause);
    }
}
