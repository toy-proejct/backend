package com.example.backend.api.infra;

import lombok.Getter;

@Getter
public class Error {
    private final String message;
    private final ErrorCode errorCode;

    public Error(String message, ErrorCode errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
