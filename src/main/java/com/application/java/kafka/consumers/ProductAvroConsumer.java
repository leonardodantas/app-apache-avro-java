package com.application.java.kafka.consumers;

import com.application.java.services.ISaveEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductAvroConsumer {

    private final ApplicationContext applicationContext;

    public ProductAvroConsumer(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @KafkaListener(
            topics = "${kafka.topics.send.product.avro}",
            groupId = "objectMapperAvro",
            containerFactory = "kafkaListenerAvro"
    )
    public void listener(
            ConsumerRecord<String, GenericRecord> record) {
        log.info("Received message with key {}", record.key());
        log.info("{}", record.value());
        log.info("-----------------------------------------\n");

        final var path = record.value().getClass().getName().split("\\.");
        final var bean = path[path.length - 1];

        final var service = applicationContext.getBean(bean, ISaveEntity.class);
        service.execute(record.value());
    }
}
