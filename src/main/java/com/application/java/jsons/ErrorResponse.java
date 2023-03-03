package com.application.java.jsons;

import java.time.LocalDateTime;

public record ErrorResponse(String message, String messageException, LocalDateTime date) {

    public static ErrorResponse from(final String messageException) {
        return new ErrorResponse("Internal server error", messageException, LocalDateTime.now());
    }
}
