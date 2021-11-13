package com.example.votingsystem.exception;

public class TimeException extends RuntimeException {

    public TimeException(String message) {
        super(message);
    }

    public TimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
