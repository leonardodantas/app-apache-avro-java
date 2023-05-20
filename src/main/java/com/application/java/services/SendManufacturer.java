package com.application.java.services;

import com.application.java.kafka.producers.IManufacturerProducer;
import org.springframework.stereotype.Service;

import static com.application.java.utils.GeneratorRandom.getManufacturer;

@Service
public class SendManufacturer {

    private final IManufacturerProducer manufacturerProducer;

    public SendManufacturer(final IManufacturerProducer manufacturerProducer) {
        this.manufacturerProducer = manufacturerProducer;
    }

    public void withAvro() {
        final var manufacturer = getManufacturer();
        manufacturerProducer.execute(manufacturer);
    }
}
