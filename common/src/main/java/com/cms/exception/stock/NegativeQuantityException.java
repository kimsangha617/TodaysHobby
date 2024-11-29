package com.cms.exception.stock;

public class NegativeQuantityException extends RuntimeException {
    public NegativeQuantityException(String message) {
        super(message);
    }
}
