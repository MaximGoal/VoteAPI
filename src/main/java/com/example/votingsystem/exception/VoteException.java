package com.example.votingsystem.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class VoteException extends RuntimeException {

    public VoteException(String message) {
        super(message);
    }

    public VoteException(String message, Throwable cause) {
        super(message, cause);
    }
}
