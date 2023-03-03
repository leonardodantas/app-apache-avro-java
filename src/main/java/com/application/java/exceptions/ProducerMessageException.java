package com.application.java.exceptions;

import java.time.LocalDateTime;

public class ProducerMessageException extends RuntimeException implements BaseException {

    private Class klass;
    private Throwable exception;
    private String message;
    private LocalDateTime date;

    public ProducerMessageException(final String message, final Throwable exception, final Class klass) {
        this.message = message;
        this.exception = exception;
        this.klass = klass;
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
