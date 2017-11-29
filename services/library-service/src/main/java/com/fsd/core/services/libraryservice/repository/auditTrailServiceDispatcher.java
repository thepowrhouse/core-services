package com.fsd.core.services.libraryservice.repository;


import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class auditTrailServiceDispatcher {

    @Autowired
    private KafkaTemplate<String, AuditTrail> auditTrailKafkaTemplate;

    @Autowired
    private KafkaProducerProperties kafkaProducerProperties;

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkUnitDispatcher.class);

    public boolean dispatch(AuditTrail auditTrail) {
        try {
            SendResult<String, AuditTrail> sendResult = auditTrailKafkaTemplate.sendDefault(auditTrail.getId(), auditTrail).get();
            RecordMetadata recordMetadata = sendResult.getRecordMetadata();
            LOGGER.info("topic = {}, partition = {}, offset = {}, workUnit = {}",
                    recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset(), user);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
