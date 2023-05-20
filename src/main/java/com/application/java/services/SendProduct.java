package com.application.java.services;

import com.application.java.kafka.producers.IProductProducer;
import org.springframework.stereotype.Service;

import static com.application.java.utils.GeneratorRandom.*;

@Service
public class SendProduct {

    private final IProductProducer productAvroProducer;
    private final IProductProducer productObjectMapperProducer;

    public SendProduct(final IProductProducer productAvroProducer, final IProductProducer productObjectMapperProducer) {
        this.productAvroProducer = productAvroProducer;
        this.productObjectMapperProducer = productObjectMapperProducer;
    }

    public void withAvro() {
        final var product = getProduct();
        productAvroProducer.execute(product);
    }

    public void withObjectMapper() {
        final var product = getProduct();
        productObjectMapperProducer.execute(product);
    }

}
