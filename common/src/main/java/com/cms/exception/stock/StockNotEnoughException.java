package com.cms.exception.stock;

public class StockNotEnoughException extends RuntimeException {
    public StockNotEnoughException(String message) {
        super(message);
    }
}
