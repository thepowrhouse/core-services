package com.fsd.core.services.libraryservice.configurations;

import com.fsd.core.services.libraryservice.models.AuditEntity;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class AuditKafkaConsumerConfig {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, AuditEntity> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, AuditEntity> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConcurrency(1);
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, AuditEntity> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerProps(), integerKeyDeserializer(), objectAuditTrailDeSerializer());
    }


    @Bean
    public Map<String, Object> consumerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "auditBookGroup");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 100);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 15000);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_DOC,"earliest");
        return props;
    }

    @Bean
    public Deserializer integerKeyDeserializer() {
        return new IntegerDeserializer();
    }

    @Bean
    public Deserializer objectAuditTrailDeSerializer() {
        return new AuditDeserializer();
    }
}