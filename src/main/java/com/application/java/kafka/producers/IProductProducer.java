package com.application.java.kafka.producers;

import com.application.java.domains.Product;

public interface IProductProducer {

    void execute(final Product product);
}
