package com.example.backend.common.exception;

public class IllegalTokenException extends RuntimeException{
    public IllegalTokenException() {
        super("토큰정보가 유효하지 않습니다");
    }

    public IllegalTokenException(String message) {
        super(message);
    }
}
