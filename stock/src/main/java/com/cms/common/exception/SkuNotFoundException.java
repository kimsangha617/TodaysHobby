package com.cms.common.exception;

public class SkuNotFoundException extends RuntimeException {

    public SkuNotFoundException(String message) {
        super(message);
    }
}
