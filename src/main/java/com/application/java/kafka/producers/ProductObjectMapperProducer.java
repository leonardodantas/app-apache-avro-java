package com.application.java.kafka.producers;

import com.application.java.domains.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
public class ProductObjectMapperProducer {

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplateObjectMapper;
    private final String topic;

    public ProductObjectMapperProducer(
            ObjectMapper objectMapper, final KafkaTemplate<String, String> kafkaTemplateObjectMapper,
            @Value("${kafka.topics.send.product.object.mapper}") final String topic
    ) {
        this.objectMapper = objectMapper;
        this.kafkaTemplateObjectMapper = kafkaTemplateObjectMapper;
        this.topic = topic;
    }

    public void execute(final Product product) {
        final var record = new ProducerRecord<>(topic, product.getCode(), getBodyAsJson(product));

        final var future = kafkaTemplateObjectMapper.send(record);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(final Throwable ex) {
                log.error("Error: {}", ex.getMessage());
            }

            @Override
            public void onSuccess(final SendResult<String, String> result) {
                log.info("Send with Object Mapper");
                log.info("OFFSET: {}, PARTITION: {}", result.getRecordMetadata().offset(), result.getRecordMetadata().partition());
            }
        });

    }

    private String getBodyAsJson(final Product product) {
        try {
            return objectMapper.writeValueAsString(product);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
