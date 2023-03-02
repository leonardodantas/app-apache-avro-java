package com.application.java.config;

import com.application.java.kafka.schemas.ProductSchema;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;

@Configuration
public class KafkaConfig {

    @Value("${kafka.bootstrapAddress}")
    private String bootstrapAddress;
    @Value("${kafka.schemaRegistry}")
    private String schemaRegistry;

    @Bean("kafkaTemplateAvro")
    public KafkaTemplate<String, ProductSchema> kafkaTemplateAvro() {
        final var properties = new HashMap<String, Object>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        properties.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistry);
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(properties));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ProductSchema> kafkaListenerAvro() {
        final var deserializer = new KafkaAvroDeserializer();

        final var properties = new HashMap<String, Object>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        properties.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
        properties.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistry);

        final var defaultKafkaConsumerFactory =
                new DefaultKafkaConsumerFactory<>(
                        properties,
                        new StringDeserializer(),
                        deserializer);

        final var factory = new ConcurrentKafkaListenerContainerFactory<String, ProductSchema>();
        factory.setConsumerFactory(defaultKafkaConsumerFactory);

        return factory;
    }

    @Bean("kafkaTemplateObjectMapper")
    public KafkaTemplate<String, String> kafkaTemplateObjectMapper() {
        final var properties = new HashMap<String, Object>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(properties));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerObjectMapper() {
        final var deserializer = new JsonDeserializer<>(String.class);
        final var properties = new HashMap<String, Object>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);

        final var defaultKafkaConsumerFactory =
                new DefaultKafkaConsumerFactory<>(
                        properties,
                        new StringDeserializer(),
                        deserializer);

        final var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        factory.setConsumerFactory(defaultKafkaConsumerFactory);

        return factory;
    }

}
