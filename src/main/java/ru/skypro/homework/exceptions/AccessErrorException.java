package ru.skypro.homework.exceptions;

public class AccessErrorException extends RuntimeException {

    public AccessErrorException() {
    }

    public AccessErrorException(String message) {
        super(message);
    }

    public AccessErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessErrorException(Throwable cause) {
        super(cause);
    }

    public AccessErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}