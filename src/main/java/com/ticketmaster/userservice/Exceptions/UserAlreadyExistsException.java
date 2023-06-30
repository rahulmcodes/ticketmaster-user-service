package com.ticketmaster.userservice.Exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class UserAlreadyExistsException extends Exception {
    private String message;
    public UserAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
    public String getMessage() {
        return this.message;
    }
}
