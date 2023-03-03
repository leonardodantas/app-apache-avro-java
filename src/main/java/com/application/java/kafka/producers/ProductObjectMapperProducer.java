package com.application.java.kafka.producers;

import com.application.java.converters.Converter;
import com.application.java.domains.Product;
import com.application.java.exceptions.ProducerMessageException;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
public class ProductObjectMapperProducer implements IProducer {

    private final Converter converter;
    private final KafkaTemplate<String, String> kafkaTemplateObjectMapper;
    private final String topic;

    public ProductObjectMapperProducer(
            final Converter converter, final KafkaTemplate<String, String> kafkaTemplateObjectMapper,
            @Value("${kafka.topics.send.product.object.mapper}") final String topic
    ) {
        this.converter = converter;
        this.kafkaTemplateObjectMapper = kafkaTemplateObjectMapper;
        this.topic = topic;
    }

    @Override
    public void execute(final Product product) {

        log.info("Create ProduceRecord:");
        log.info("TOPIC: {}", topic);
        log.info("KEY: {}", product.getCode());
        final var record = new ProducerRecord<>(topic, product.getCode(), converter.execute(product));

        final var future = kafkaTemplateObjectMapper.send(record);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(final Throwable ex) {
                log.error("Error: {}", ex.getMessage());
                throw new ProducerMessageException("Error producer message with object mapper", ex, ProductObjectMapperProducer.class);
            }

            @Override
            public void onSuccess(final SendResult<String, String> result) {
                log.info("Send with Object Mapper");
                log.info("OFFSET: {}, PARTITION: {}", result.getRecordMetadata().offset(), result.getRecordMetadata().partition());
                log.info("Serialized Value Size = {}", result.getRecordMetadata().serializedValueSize());
            }
        });

    }

}
