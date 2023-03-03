package com.application.java.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ConverterException extends RuntimeException {

    private final Class klass;
    private final Throwable exception;
    private final String message;
    private final LocalDateTime date;

    public ConverterException(final Class klass, final Throwable exception, final String message) {
        this.klass = klass;
        this.exception = exception;
        this.message = message;
        this.date = LocalDateTime.now();
    }

    public String getMessageException() {
        return this.exception.getMessage();
    }
}
