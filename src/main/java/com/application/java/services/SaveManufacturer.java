package com.application.java.services;

import com.application.java.kafka.schemas.ManufacturerSchema;
import com.application.java.kafka.schemas.ProductSchema;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.avro.generic.GenericRecord;
import org.springframework.stereotype.Service;

@Service("ManufacturerSchema")
public class SaveManufacturer implements ISaveEntity {

    private final ObjectMapper objectMapper;

    public SaveManufacturer(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void execute(final GenericRecord record) {
        System.out.println("Execute SaveManufacturer");
        try {
            final var manufacturerSchema = objectMapper.readValue(String.valueOf(record), ManufacturerSchema.class);
            System.out.println(manufacturerSchema);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
