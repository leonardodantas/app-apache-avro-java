package com.application.java.kafka.producers;

import com.application.java.domains.Product;

public interface IProducer {

    void execute(final Product product);
}
