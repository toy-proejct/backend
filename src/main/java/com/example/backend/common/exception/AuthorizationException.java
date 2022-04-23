package com.example.backend.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthorizationException extends RuntimeException {
    public AuthorizationException(Code code) {
        super(code.getMessage());
    }

    public enum Code {
        WRONG_MEMBER_INFO("사용자정보가 일치하지 않습니다")
        ;

        private final String message;

        Code(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
