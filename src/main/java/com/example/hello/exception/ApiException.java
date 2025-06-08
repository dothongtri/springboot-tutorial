package com.example.hello.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {
    private final HttpStatus status;

    public ApiException(HttpStatus unauthorized, String message) {
        super(message);
        this.status = unauthorized;
    }

    public HttpStatus getStatus() {
        return status;
    }
}

