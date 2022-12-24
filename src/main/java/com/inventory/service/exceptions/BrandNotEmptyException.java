package com.inventory.service.exceptions;

public class BrandNotEmptyException extends RuntimeException {
    public BrandNotEmptyException() {
    }

    public BrandNotEmptyException(String message) {
        super(message);
    }

    public BrandNotEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public BrandNotEmptyException(Throwable cause) {
        super(cause);
    }
}
