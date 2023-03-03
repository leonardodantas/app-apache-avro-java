package com.application.java.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProducerMessageException extends RuntimeException {

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

    public String getMessageException() {
        return this.exception.getMessage();
    }
}
