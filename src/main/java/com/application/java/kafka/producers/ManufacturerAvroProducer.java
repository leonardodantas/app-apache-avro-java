package com.application.java.kafka.producers;

import com.application.java.converters.ManufacturerToManufacturerSchema;
import com.application.java.domains.Manufacturer;
import com.application.java.exceptions.ProducerMessageException;
import com.application.java.kafka.schemas.ManufacturerSchema;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
public class ManufacturerAvroProducer implements IManufacturerProducer {

    private final ManufacturerToManufacturerSchema converter;
    private final KafkaTemplate<String, ManufacturerSchema> manufacturerKafkaTemplateAvro;
    private final String topic;

    public ManufacturerAvroProducer(
            final ManufacturerToManufacturerSchema converter,
            final KafkaTemplate<String, ManufacturerSchema> manufacturerKafkaTemplateAvro,
            @Value("${kafka.topics.send.product.avro}") final String topic
    ) {
        this.converter = converter;
        this.manufacturerKafkaTemplateAvro = manufacturerKafkaTemplateAvro;
        this.topic = topic;
    }

    @Override
    public void execute(final Manufacturer manufacturer) {

        log.info("Create ProduceRecord:");
        log.info("TOPIC: {}", topic);
        log.info("KEY: {}", manufacturer.getId());
        final var record = new ProducerRecord<>(topic, manufacturer.getId(), converter.convert(manufacturer));

        final var future = manufacturerKafkaTemplateAvro.send(record);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(final Throwable ex) {
                log.error("Error: {}", ex.getMessage());
                throw new ProducerMessageException("Error producer message with avro", ex, ManufacturerAvroProducer.class);
            }

            @Override
            public void onSuccess(final SendResult<String, ManufacturerSchema> result) {
                log.info("Send with Apache Avro");
                log.info("OFFSET: {}, PARTITION: {}", result.getRecordMetadata().offset(), result.getRecordMetadata().partition());
                log.info("Serialized Value Size = {}", result.getRecordMetadata().serializedValueSize());
            }
        });

    }
}
