package com.fsd.core.services.libraryservice.configurations;

import com.fsd.core.services.libraryservice.models.AuditEntity;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AuditKafkaProducerConfig {

    @Bean
    public ProducerFactory<Integer, AuditEntity> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs(), integerKeySerializer(),objectAuditTrailSerializer());
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        return props;
    }

    @Bean
    public KafkaTemplate<Integer, AuditEntity> workUnitsKafkaTemplate() {
        KafkaTemplate<Integer, AuditEntity> kafkaTemplate =  new KafkaTemplate<>(producerFactory());
        kafkaTemplate.setDefaultTopic("bookStoreTopic");
        return kafkaTemplate;
    }

    @Bean
    public Serializer integerKeySerializer() {
        return new IntegerSerializer();
    }

    @Bean
    public Serializer objectAuditTrailSerializer() {
        return new AuditSerializer();
    }
}