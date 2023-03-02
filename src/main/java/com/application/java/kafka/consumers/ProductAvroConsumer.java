package com.application.java.kafka.consumers;

import com.application.java.converters.ProductSchemaToProduct;
import com.application.java.kafka.schemas.ProductSchema;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductAvroConsumer {

    private final ProductSchemaToProduct converter;

    public ProductAvroConsumer(final ProductSchemaToProduct converter) {
        this.converter = converter;
    }

    @KafkaListener(
            topics = "${kafka.topics.send.product.avro}",
            groupId = "objectMapperAvro",
            containerFactory = "kafkaListenerAvro"
    )
    public void listener(
            @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) final String key,
            @Payload final ConsumerRecord<String, ProductSchema> message
    ) {
        log.info("Received message with key {}", key);
        final var product = converter.convert(message.value());
        log.info("{}", product);
        log.info("-----------------------------------------\n");
    }
}
