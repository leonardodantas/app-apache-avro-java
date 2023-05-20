package com.application.java.kafka.producers;

import com.application.java.domains.Manufacturer;

public interface IManufacturerProducer {

    void execute(final Manufacturer manufacturer);
}
