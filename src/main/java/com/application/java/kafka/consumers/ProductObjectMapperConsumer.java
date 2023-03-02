package com.application.java.kafka.consumers;

import com.application.java.domains.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductObjectMapperConsumer {

    private final ObjectMapper objectMapper;

    public ProductObjectMapperConsumer(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
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
        final var product = getStringAsProduct(message);
        log.info("{}", product);
    }

    private Product getStringAsProduct(final String message) {
        try {
            return objectMapper.readValue(message, Product.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
