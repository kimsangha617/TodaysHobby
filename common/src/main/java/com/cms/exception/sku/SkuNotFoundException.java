package com.cms.exception.sku;

public class SkuNotFoundException extends RuntimeException {

    public SkuNotFoundException(String message) {
        super(message);
    }
}
