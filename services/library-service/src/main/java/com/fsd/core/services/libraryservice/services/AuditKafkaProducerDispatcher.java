package com.fsd.core.services.libraryservice.services;

import com.fsd.core.services.libraryservice.models.Audit;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

public class AuditKafkaProducerDispatcher {
    @Autowired
    private KafkaTemplate<String, Audit> auditTrailKafkaTemplate;

    public boolean dispatch(Audit audit) {
        try {
            SendResult<String, Audit> sendResult = auditTrailKafkaTemplate.sendDefault(audit.getBookId(), audit).get();
            RecordMetadata recordMetadata = sendResult.getRecordMetadata();
            System.out.println("topic = {}, partition = {}, offset = {}, workUnit = {}" +
                    recordMetadata.topic() + recordMetadata.partition() + recordMetadata.offset() + audit);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
