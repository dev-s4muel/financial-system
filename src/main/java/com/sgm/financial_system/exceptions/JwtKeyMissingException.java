package com.sgm.financial_system.exceptions;


public class JwtKeyMissingException extends RuntimeException {
    public JwtKeyMissingException(String message) {
        super(message);
    }
    public JwtKeyMissingException() {}
}
