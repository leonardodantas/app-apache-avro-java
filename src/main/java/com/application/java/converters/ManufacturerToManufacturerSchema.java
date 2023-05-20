package com.application.java.converters;

import com.application.java.domains.Manufacturer;
import com.application.java.kafka.schemas.ManufacturerSchema;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ManufacturerToManufacturerSchema implements Converter<Manufacturer, ManufacturerSchema> {

    @Override
    public ManufacturerSchema convert(final Manufacturer manufacturer) {
        return ManufacturerSchema.newBuilder()
                .setId(manufacturer.getId())
                .setAddress(manufacturer.getAddress())
                .setName(manufacturer.getName())
                .setPhone(manufacturer.getPhone())
                .build();
    }
}
