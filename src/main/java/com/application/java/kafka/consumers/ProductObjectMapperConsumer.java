package com.application.java.kafka.consumers;

import com.application.java.converters.Converter;
import com.application.java.domains.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductObjectMapperConsumer {

    private final Converter converter;

    public ProductObjectMapperConsumer(final Converter converter) {
        this.converter = converter;
    }

    @KafkaListener(
            topics = "${kafka.topics.send.product.object.mapper}",
            groupId = "objectMapperConsumer",
            containerFactory = "kafkaListenerObjectMapper"
    )
    public void listener(
            @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) final String key,
            @Payload final String message
    ) {
        log.info("Received message with key {}", key);
        final var product = converter.execute(message, Product.class);
        log.info("{}", product);
        log.info("-----------------------------------------\n");
    }

}
