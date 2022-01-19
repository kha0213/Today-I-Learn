package com.test.toyproject1.exception;

public class NotEnoughStockException extends IllegalAccessException {
    public NotEnoughStockException(String msg) {
        super(msg);
    }
}
