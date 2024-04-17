package com.RailwayManagementSystem.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class NoAvailableSeatsException extends RuntimeException {
public NoAvailableSeatsException(String message) {
        super(message);
        }
        }

