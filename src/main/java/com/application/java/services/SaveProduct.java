package com.application.java.services;

import com.application.java.kafka.schemas.ManufacturerSchema;
import com.application.java.kafka.schemas.ProductSchema;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.avro.generic.GenericRecord;
import org.springframework.stereotype.Service;


@Service("ProductSchema")
public class SaveProduct implements ISaveEntity {

    private final ObjectMapper objectMapper;

    public SaveProduct(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void execute(final GenericRecord record) {
        System.out.println("Execute SaveProduct");
        try {
            final var productSchema = objectMapper.readValue(String.valueOf(record), ProductSchema.class);
            System.out.println(productSchema);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
