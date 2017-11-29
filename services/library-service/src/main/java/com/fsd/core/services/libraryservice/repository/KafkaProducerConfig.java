package com.fsd.core.services.libraryservice.repository;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import com.fsd.core.services.libraryservice.models.AuditTrail;
import com.fsd.core.services.libraryservice.models.AuditTrailSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Autowired
    private KafkaProducerProperties kafkaProducerProperties;

    @Bean
    public ProducerFactory<String, AuditTrail> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs(), stringKeySerializer(),objectAuditTrailSerializer());
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProducerProperties.getBootstrap());
        return props;
    }

    @Bean
    public KafkaTemplate<String, AuditTrail> workUnitsKafkaTemplate() {
        KafkaTemplate<String, AuditTrail> kafkaTemplate =  new KafkaTemplate<>(producerFactory());
        kafkaTemplate.setDefaultTopic(kafkaProducerProperties.getTopic());
        return kafkaTemplate;
    }

    @Bean
    public Serializer stringKeySerializer() {
        return new StringSerializer();
    }

    @Bean
    public Serializer objectAuditTrailSerializer() {
        return new AuditTrailSerializer();
    }
}
