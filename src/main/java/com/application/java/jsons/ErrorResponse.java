package com.application.java.jsons;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public record ErrorResponse(String message, String messageException, LocalDateTime date) {

}
