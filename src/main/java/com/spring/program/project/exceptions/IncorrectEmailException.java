package com.spring.program.project.exceptions;

/**
 * @author DacaP
 * @version 1.0
 */
public class IncorrectEmailException extends RuntimeException {

    public IncorrectEmailException() {
    }

    public IncorrectEmailException(String message) {
        super(message);
    }

    public IncorrectEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
