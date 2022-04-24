package com.example.backend.common.exception;

public class IllegalTokenException extends RuntimeException{
    public IllegalTokenException(String message) {
        super(message);
    }
}
