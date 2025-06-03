package com.sgm.financial_system.exceptions;


public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
    public UserNotFoundException() {}
}
