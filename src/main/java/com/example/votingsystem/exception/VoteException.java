package com.example.votingsystem.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class VoteException extends RuntimeException {

    Integer userId;

    public VoteException(String message) {
        super(message);
    }

    public VoteException(String message, Integer userId) {
        super(message);
        this.userId = userId;
    }

    public VoteException(String message, Throwable cause) {
        super(message, cause);
    }

    public VoteException(String message, Throwable cause, Integer userId) {
        super(message, cause);
        this.userId = userId;
    }
}
