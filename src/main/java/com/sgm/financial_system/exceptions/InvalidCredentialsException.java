package com.sgm.financial_system.exceptions;


public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
    public InvalidCredentialsException() {}
}
