package com.application.java.kafka.producers;

import com.application.java.converters.ProductToProductSchema;
import com.application.java.domains.Product;
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
public class ProductAvroProducer {

    private final ProductToProductSchema converter;
    private final KafkaTemplate<String, ProductSchema> kafkaTemplateAvro;
    private final String topic;

    public ProductAvroProducer(
            final ProductToProductSchema converter,
            final KafkaTemplate<String, ProductSchema> kafkaTemplateAvro,
            @Value("${kafka.topics.send.product.avro}") final String topic
    ) {
        this.converter = converter;
        this.kafkaTemplateAvro = kafkaTemplateAvro;
        this.topic = topic;
    }

    public void execute(final Product product) {
        final var productSchema = converter.convert(product);
        final var record = new ProducerRecord<>(topic, product.getCode(), productSchema);

        final var future = kafkaTemplateAvro.send(record);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(final Throwable ex) {
                log.error("Error: {}", ex.getMessage());
            }

            @Override
            public void onSuccess(final SendResult<String, ProductSchema> result) {
                log.info("Send with Apache Avro");
                log.info("OFFSET: {}, PARTITION: {}", result.getRecordMetadata().offset(), result.getRecordMetadata().partition());
            }
        });

    }
}
