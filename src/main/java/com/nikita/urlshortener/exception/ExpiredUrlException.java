package com.nikita.urlshortener.exception;

public class ExpiredUrlException extends RuntimeException {
    public ExpiredUrlException(String message) {
        super(message);
    }
}
