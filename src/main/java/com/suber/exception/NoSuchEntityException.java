package com.suber.exception;

public class NoSuchEntityException extends RuntimeException {

    public NoSuchEntityException() {
        super();
    }
    public NoSuchEntityException(String message, Throwable cause) {
        super(message, cause);
    }
    public NoSuchEntityException(String message) {
        super(message);
    }
    public NoSuchEntityException(Throwable cause) {
        super(cause);
    }

}