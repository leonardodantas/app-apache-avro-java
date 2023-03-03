package com.application.java.controllers;

import com.application.java.exceptions.ConverterException;
import com.application.java.exceptions.ProducerMessageException;
import com.application.java.jsons.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(ProducerMessageException.class)
    public ResponseEntity<?> handlerProducerMessage(final ProducerMessageException exception) {
        final var response = new ErrorResponse(exception.getMessage(), exception.getMessageException(), exception.getDate());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ConverterException.class)
    public ResponseEntity<?> handlerConverterException(final ConverterException exception) {
        final var response = new ErrorResponse(exception.getMessage(), exception.getMessageException(), exception.getDate());
        return ResponseEntity.badRequest().body(response);
    }
}
