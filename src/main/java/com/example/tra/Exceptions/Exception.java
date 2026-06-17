package com.example.tra.Exceptions;

import org.springframework.http.HttpStatus;

public class Exception extends RuntimeException{
    private HttpStatus status;

    public Exception(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public static Exception notFound(String message) {
        return new Exception(HttpStatus.NOT_FOUND, message);
    }

    public static Exception badRequest(String message) {
        return new Exception(HttpStatus.BAD_REQUEST, message);
    }

    public HttpStatus getStatus() {
        return status;
    }

}
