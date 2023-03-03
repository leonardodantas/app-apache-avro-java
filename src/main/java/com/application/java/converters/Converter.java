package com.application.java.converters;

import com.application.java.exceptions.ConverterException;
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
        } catch (final JsonProcessingException exception) {
            log.error("Error converter string to object: {}", exception.getMessage());
            throw new ConverterException(Converter.class, exception, String.format("Error converter String to Object: %s", object.getName()));
        }
    }

    public String execute(final Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException exception) {
            log.error("Error converter object to string: {}", exception.getMessage());
            throw new ConverterException(Converter.class, exception, "Error converter object to String");
        }
    }
}
