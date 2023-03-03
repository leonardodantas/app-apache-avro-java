package com.application.java.exceptions;

import java.time.LocalDateTime;

public class ConverterException extends RuntimeException implements BaseException {

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

    @Override
    public Class getKlass() {
        return klass;
    }

    @Override
    public Throwable getException() {
        return exception;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getMessageException() {
        return this.exception.getMessage();
    }

    @Override
    public LocalDateTime getDate() {
        return date;
    }
}
