package com.application.java.kafka.producers;

import com.application.java.converters.ProductToProductSchema;
import com.application.java.domains.Product;
import com.application.java.exceptions.ProducerMessageException;
import com.application.java.kafka.schemas.ProductSchema;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
public class ProductAvroProducer implements IProductProducer {

    private final ProductToProductSchema converter;
    private final KafkaTemplate<String, ProductSchema> productKafkaTemplateAvro;
    private final String topic;

    public ProductAvroProducer(
            final ProductToProductSchema converter,
            final KafkaTemplate<String, ProductSchema> productKafkaTemplateAvro,
            @Value("${kafka.topics.send.product.avro}") final String topic
    ) {
        this.converter = converter;
        this.productKafkaTemplateAvro = productKafkaTemplateAvro;
        this.topic = topic;
    }

    @Override
    public void execute(final Product product) {

        log.info("Create ProduceRecord:");
        log.info("TOPIC: {}", topic);
        log.info("KEY: {}", product.getCode());
        final var record = new ProducerRecord<>(topic, product.getCode(), converter.convert(product));

        final var future = productKafkaTemplateAvro.send(record);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(final Throwable ex) {
                log.error("Error: {}", ex.getMessage());
                throw new ProducerMessageException("Error producer message with avro", ex, ProductAvroProducer.class);
            }

            @Override
            public void onSuccess(final SendResult<String, ProductSchema> result) {
                log.info("Send with Apache Avro");
                log.info("OFFSET: {}, PARTITION: {}", result.getRecordMetadata().offset(), result.getRecordMetadata().partition());
                log.info("Serialized Value Size = {}", result.getRecordMetadata().serializedValueSize());
            }
        });

    }
}
