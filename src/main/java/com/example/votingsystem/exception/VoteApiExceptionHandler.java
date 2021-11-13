package com.example.votingsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class VoteApiExceptionHandler {

    @ExceptionHandler(value = {VoteException.class, TimeException.class})
    public ResponseEntity<Object> handleAlreadyVotedException (RuntimeException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(   e.getMessage(),
                                                        e,
                                                        badRequest,
                                                        ZonedDateTime.now());
        return new ResponseEntity<>(apiException, badRequest);
    }

}
