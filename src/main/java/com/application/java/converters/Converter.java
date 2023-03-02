package com.application.java.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Converter {

    private final ObjectMapper objectMapper;

    public Converter(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> T execute(final String json, final Class<T> object) {
        try {
            return objectMapper.readValue(json, object);
        } catch (final JsonProcessingException e) {
            //TODO
            /**
             * Criar exceptions
             */
            log.error("Error converter string to object: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String execute(final Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            //TODO
            /**
             * Criar exceptions
             */
            log.error("Error converter object to string: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
