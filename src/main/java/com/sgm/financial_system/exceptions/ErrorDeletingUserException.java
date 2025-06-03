package com.sgm.financial_system.exceptions;


public class ErrorDeletingUserException extends RuntimeException {
    public ErrorDeletingUserException(String message) {
        super(message);
    }
    public ErrorDeletingUserException() {}
}
