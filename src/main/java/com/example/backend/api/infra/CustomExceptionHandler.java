package com.example.backend.api.infra;

import com.example.backend.common.exception.IllegalTokenException;
import com.example.backend.common.exception.UnidentifiedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException exception) {
        logger.error("message", exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new Error(exception.getMessage(), ErrorCode.ENTITY_NOT_FOUND));
    }

    @ExceptionHandler(UnidentifiedException.class)
    public ResponseEntity<?> handleUnidentifiedException(UnidentifiedException exception) {
        logger.error("message", exception);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new Error(exception.getMessage(), ErrorCode.ARGUMENT_DOES_NOT_MATCH));
    }

    @ExceptionHandler(IllegalTokenException.class)
    public ResponseEntity<?> handleIllegalTokenException(IllegalTokenException exception) {
        logger.error("message", exception);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new Error(exception.getMessage(), ErrorCode.ILLEGAL_TOKEN));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException(NullPointerException exception) {
        logger.error("message", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Error(exception.getMessage(), ErrorCode.NO_DATA));
    }
}
