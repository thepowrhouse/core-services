package com.fsd.core.services.libraryservice.configurations;

import com.fsd.core.services.libraryservice.models.AuditEntity;
import org.apache.kafka.clients.producer.ProducerConfig;
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
    public ProducerFactory<String, AuditEntity> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs(), stringKeySerializer(),objectAuditTrailSerializer());
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.99.100:9092");
        return props;
    }

    @Bean
    public KafkaTemplate<String, AuditEntity> workUnitsKafkaTemplate() {
        KafkaTemplate<String, AuditEntity> kafkaTemplate =  new KafkaTemplate<>(producerFactory());
        kafkaTemplate.setDefaultTopic("auditBook");
        return kafkaTemplate;
    }

    @Bean
    public Serializer stringKeySerializer() {
        return new StringSerializer();
    }

    @Bean
    public Serializer objectAuditTrailSerializer() {
        return new AuditSerializer();
    }
}