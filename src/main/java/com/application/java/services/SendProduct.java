package com.application.java.services;

import com.application.java.domains.Product;
import com.application.java.domains.Promotion;
import com.application.java.kafka.producers.ProductAvroProducer;
import com.application.java.kafka.producers.ProductObjectMapperProducer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static com.application.java.utils.GeneratorRandom.*;

@Service
public class SendProduct {

    private final ProductAvroProducer productAvroProducer;
    private final ProductObjectMapperProducer productObjectMapperProducer;

    public SendProduct(final ProductAvroProducer productAvroProducer, final ProductObjectMapperProducer productObjectMapperProducer) {
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
