package com.cms.exception;

public class SkuNotFoundException extends RuntimeException {

    public SkuNotFoundException(String message) {
        super(message);
    }
}
