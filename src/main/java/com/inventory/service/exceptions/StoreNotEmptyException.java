package com.inventory.service.exceptions;

public class StoreNotEmptyException extends RuntimeException {
    public StoreNotEmptyException() {
    }

    public StoreNotEmptyException(String message) {
        super(message);
    }

    public StoreNotEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public StoreNotEmptyException(Throwable cause) {
        super(cause);
    }
}
